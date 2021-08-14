package ru.israpil.cbrcurrency.data.currency.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeJsonAdapter {
    @FromJson
    fun fromJson(value: String): LocalDateTime =
        LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME)

    @ToJson
    fun toJson(value: LocalDateTime): String = value.format(DateTimeFormatter.ISO_DATE_TIME)
}
