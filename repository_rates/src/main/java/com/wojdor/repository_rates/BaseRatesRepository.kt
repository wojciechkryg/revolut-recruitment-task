package com.wojdor.repository_rates

import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import io.reactivex.Single

interface BaseRatesRepository {

    fun getRates(baseCurrency: Currency): Single<Rates>
}