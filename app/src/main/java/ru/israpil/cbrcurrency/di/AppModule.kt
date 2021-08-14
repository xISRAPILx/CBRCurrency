package ru.israpil.cbrcurrency.di

import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.israpil.cbrcurrency.BuildConfig
import ru.israpil.cbrcurrency.data.currency.CBRCurrencyRepository
import ru.israpil.cbrcurrency.data.currency.adapter.BigDecimalJsonAdapter
import ru.israpil.cbrcurrency.data.currency.adapter.LocalDateTimeJsonAdapter
import ru.israpil.cbrcurrency.data.currency.api.CBRCurrencyApi
import ru.israpil.cbrcurrency.domain.currency.CurrencyRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(BigDecimalJsonAdapter())
        .add(LocalDateTimeJsonAdapter())
        .build()

    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideCBRCurrencyApi(retrofit: Retrofit): CBRCurrencyApi =
        retrofit.create(CBRCurrencyApi::class.java)

    private const val API_BASE_URL = "https://cbr-xml-daily.ru/"
}

@Module
@InstallIn(SingletonComponent::class)
interface AppBindsModule {
    @Binds
    fun bindCurrencyRepo(impl: CBRCurrencyRepository): CurrencyRepository
}
