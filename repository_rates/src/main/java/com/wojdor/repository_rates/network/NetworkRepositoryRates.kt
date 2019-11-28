package com.wojdor.repository_rates.network

import com.wojdor.data.RatesDto
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.RepositoryRates
import io.reactivex.Single

class NetworkRepositoryRates(private val apiService: RatesApi) : RepositoryRates {

    override fun getRates(baseCurrency: Currency): Single<RatesDto> =
        apiService.getRates(baseCurrency.toString())
}