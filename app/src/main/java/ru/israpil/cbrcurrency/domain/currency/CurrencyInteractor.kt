package ru.israpil.cbrcurrency.domain.currency

import ru.israpil.cbrcurrency.domain.base.safeRequest
import javax.inject.Inject

class CurrencyInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend fun getCurrencies() = safeRequest {
        currencyRepository.getCurrencies()
    }
}
