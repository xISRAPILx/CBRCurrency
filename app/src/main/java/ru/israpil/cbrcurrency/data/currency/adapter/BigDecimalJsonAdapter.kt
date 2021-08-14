package ru.israpil.cbrcurrency.data.currency.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

class BigDecimalJsonAdapter {
    @FromJson
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}
