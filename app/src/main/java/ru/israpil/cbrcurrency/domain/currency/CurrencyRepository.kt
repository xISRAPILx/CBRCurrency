package ru.israpil.cbrcurrency.domain.currency

import ru.israpil.cbrcurrency.domain.currency.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencies(): List<Currency>
}
