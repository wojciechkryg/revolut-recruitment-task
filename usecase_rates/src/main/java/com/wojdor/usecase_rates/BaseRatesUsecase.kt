package com.wojdor.usecase_rates

import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import io.reactivex.Observable

interface BaseRatesUsecase {

    fun getRatesWithInterval(
        baseCurrency: Currency,
        seconds: Long = RATES_INTERVAL
    ): Observable<Rates>

    companion object {
        private const val RATES_INTERVAL = 1L
    }
}