package ru.israpil.cbrcurrency.data.currency.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class CurrencyRes(
    @Json(name = "ID")
    val id: String,
    @Json(name = "NumCode")
    val numCode: Short,
    @Json(name = "CharCode")
    val charCode: String,
    @Json(name = "Nominal")
    val nominal: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Value")
    val value: BigDecimal,
    @Json(name = "Previous")
    val prevValue: BigDecimal
)
