package ru.israpil.cbrcurrency.data.currency.api

import retrofit2.http.GET
import ru.israpil.cbrcurrency.data.currency.model.CurrenciesRes

interface CBRCurrencyApi {
    @GET("daily_json.js")
    suspend fun getCurrencies(): CurrenciesRes
}
