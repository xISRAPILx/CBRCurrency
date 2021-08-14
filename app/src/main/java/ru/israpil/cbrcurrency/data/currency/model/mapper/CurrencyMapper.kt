package ru.israpil.cbrcurrency.data.currency.model.mapper

import ru.israpil.cbrcurrency.data.currency.model.CurrencyRes
import ru.israpil.cbrcurrency.domain.currency.model.Currency
import javax.inject.Inject

class CurrencyMapper @Inject constructor() {
    fun map(from: CurrencyRes): Currency = Currency(
        id = from.id,
        charCode = from.charCode,
        value = from.value,
        prevValue = from.prevValue,
        name = from.name
    )
}
