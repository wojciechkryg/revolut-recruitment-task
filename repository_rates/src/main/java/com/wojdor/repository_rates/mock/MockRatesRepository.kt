package com.wojdor.repository_rates.mock

import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.BaseRatesRepository
import io.reactivex.Single
import kotlin.random.Random

class MockRatesRepository : BaseRatesRepository {

    private val mockDate by lazy { "2018-09-06" }
    private val mockMap by lazy {
        mutableMapOf<Currency, Double>().apply {
            Currency.values().forEach {
                put(it, Random.nextDouble(0.1, 3.0))
            }
        }
    }

    override fun getRates(baseCurrency: Currency): Single<Rates> =
        Single.just(Rates(mockDate, mockMap))
}