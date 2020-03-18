package com.wojdor.repository_rates.mock

import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.BaseRatesRepository
import io.reactivex.Single
import java.math.BigDecimal
import kotlin.random.Random

class MockRatesRepository : BaseRatesRepository {

    override fun getRates(baseCurrency: Currency): Single<Rates> =
        Single.just(Rates(getMockList(baseCurrency)))

    private fun getMockList(baseCurrency: Currency) = mutableListOf<Rate>().apply {
        add(Rate(baseCurrency, BigDecimal.ONE))
        Currency.values().filterNot { baseCurrency == it }.forEach {
            add(Rate(it, BigDecimal(Random.nextDouble(RANDOM_FROM, RANDOM_TO))))
        }
    }

    companion object {
        private const val RANDOM_FROM = 0.1
        private const val RANDOM_TO = 3.0
    }
}