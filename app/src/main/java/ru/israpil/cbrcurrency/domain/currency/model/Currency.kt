package ru.israpil.cbrcurrency.domain.currency.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

typealias CurrencyList = List<Currency>

@Parcelize
data class Currency(
    val id: String,
    val charCode: String,
    val name: String,
    val value: BigDecimal,
    val prevValue: BigDecimal
) : Parcelable{
    val up get() = value > prevValue
}
