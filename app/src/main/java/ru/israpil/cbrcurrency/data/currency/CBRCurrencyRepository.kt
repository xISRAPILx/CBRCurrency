package ru.israpil.cbrcurrency.data.currency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.israpil.cbrcurrency.data.currency.api.CBRCurrencyApi
import ru.israpil.cbrcurrency.data.currency.model.mapper.CurrencyMapper
import ru.israpil.cbrcurrency.domain.currency.CurrencyRepository
import ru.israpil.cbrcurrency.domain.currency.model.Currency
import javax.inject.Inject

class CBRCurrencyRepository @Inject constructor(
    private val cbrCurrencyApi: CBRCurrencyApi,
    private val currencyMapper: CurrencyMapper
) : CurrencyRepository {
    override suspend fun getCurrencies(): List<Currency> = withContext(Dispatchers.IO) {
        cbrCurrencyApi.getCurrencies().let { res ->
            res.valute.map { entry -> currencyMapper.map(entry.value) }
        }
    }
}
