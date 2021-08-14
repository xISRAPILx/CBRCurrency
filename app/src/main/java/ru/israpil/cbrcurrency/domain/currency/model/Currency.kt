package ru.israpil.cbrcurrency.domain.currency.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Currency(
    val id: String,
    val charCode: String,
    val name: String,
    val value: BigDecimal,
    val prevValue: BigDecimal
) : Parcelable
