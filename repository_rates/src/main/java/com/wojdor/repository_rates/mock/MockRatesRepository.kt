package com.wojdor.repository_rates.mock

import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.BaseRatesRepository
import io.reactivex.Single
import kotlin.random.Random

class MockRatesRepository : BaseRatesRepository {

    private val mockDate by lazy { "2018-09-06" }
    private val mockList by lazy {
        mutableListOf<Rate>().apply {
            Currency.values().forEach {
                add(Rate(it, Random.nextDouble(0.1, 3.0)))
            }
        }
    }

    override fun getRates(baseCurrency: Currency): Single<Rates> =
        Single.just(Rates(mockDate, mockList))
}