package ru.israpil.cbrcurrency.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.israpil.cbrcurrency.domain.currency.CurrencyInteractor
import ru.israpil.cbrcurrency.domain.currency.model.CurrencyList
import ru.israpil.cbrcurrency.view.base.ViewState
import ru.israpil.cbrcurrency.view.base.loadResource
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val interactor: CurrencyInteractor
) : ViewModel() {
    private val _currenciesState = MutableStateFlow<ViewState<CurrencyList>>(ViewState.Loading)
    val currenciesState = _currenciesState.asStateFlow()

    init {
        _currenciesState.loadResource(viewModelScope) {
            interactor.getCurrencies()
        }
    }

    fun onRefresh() {
        val state = _currenciesState.value
        if (state is ViewState.Show) {
            _currenciesState.loadResource(
                coroutineScope = viewModelScope,
                loadingState = state.refresh()
            ) {
                interactor.getCurrencies()
            }
        } else {
            throw IllegalStateException("Cannot refresh on state $state")
        }
    }
}
