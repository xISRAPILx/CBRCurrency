package ru.israpil.cbrcurrency.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.israpil.cbrcurrency.domain.currency.CurrencyInteractor
import ru.israpil.cbrcurrency.domain.currency.model.Currency
import ru.israpil.cbrcurrency.view.base.ViewState
import ru.israpil.cbrcurrency.view.base.asLiveData
import ru.israpil.cbrcurrency.view.base.asViewState
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val interactor: CurrencyInteractor
) : ViewModel() {

    private val _currenciesState = MutableLiveData<ViewState<List<Currency>>>()
    val currenciesState = _currenciesState.asLiveData()

    private val _isLoading = MutableLiveData(true)
    val isLoading = _isLoading.asLiveData()

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing = _isRefreshing.asLiveData()

    init {
        loadCurrencies(_isLoading)
    }

    private fun loadCurrencies(indicator: MutableLiveData<Boolean>) {
        indicator.value = true
        viewModelScope.launch {
            _currenciesState.value = interactor.getCurrencies().asViewState()
            indicator.value = false
        }
    }

    fun onCurrenciesRefresh() {
        loadCurrencies(_isRefreshing)
    }
}
