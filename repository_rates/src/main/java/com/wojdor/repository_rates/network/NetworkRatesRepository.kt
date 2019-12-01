package com.wojdor.repository_rates.network

import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.domain.mapper.RatesMapper
import com.wojdor.repository_rates.BaseRatesRepository
import io.reactivex.Single

class NetworkRatesRepository(private val apiService: RatesApi) : BaseRatesRepository {

    override fun getRates(baseCurrency: Currency): Single<Rates> =
        apiService.getRates(baseCurrency.toString())
            .map { RatesMapper.map(it) }
}