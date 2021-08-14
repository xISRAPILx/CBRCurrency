package ru.israpil.cbrcurrency.data.currency.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class CurrenciesRes(
    @Json(name = "Date")
    val date: LocalDateTime,
    @Json(name = "PreviousDate")
    val previousDate: LocalDateTime,
    @Json(name = "Valute")
    val valute: Map<String, CurrencyRes>
)
