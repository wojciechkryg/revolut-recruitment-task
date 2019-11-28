package com.wojdor.repository_rates

import com.wojdor.data.RatesDto
import com.wojdor.domain.enums.Currency
import io.reactivex.Single

interface RepositoryRates {

    fun getRates(
        baseCurrency: Currency = Currency.EUR
    ): Single<RatesDto>
}