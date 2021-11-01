package ru.israpil.cbrcurrency.view.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.israpil.cbrcurrency.domain.base.RequestResult

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    object Empty : ViewState<Nothing>()
    data class Show<T>(
        val data: T,
        val isRefreshing: Boolean = false
    ) : ViewState<T>(){
        fun refresh() = copy(isRefreshing = true)
    }

    data class Error(val throwable: Throwable) : ViewState<Nothing>()
}

inline fun <T> MutableStateFlow<ViewState<T>>.loadResource(
    coroutineScope: CoroutineScope,
    loadingState: ViewState<T> = ViewState.Loading,
    crossinline block: suspend () -> RequestResult<T>
) {
    value = loadingState
    coroutineScope.launch {
        value = when (val result = block.invoke()) {
            is RequestResult.Success -> when {
                result.data == null -> ViewState.Empty
                result.data is Collection<*> && result.data.isEmpty() -> ViewState.Empty
                else -> ViewState.Show(result.data)
            }
            is RequestResult.Failed.Error -> ViewState.Error(result.throwable)
        }
    }
}
