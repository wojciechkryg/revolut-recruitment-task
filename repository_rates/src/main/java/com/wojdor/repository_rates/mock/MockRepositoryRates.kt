package com.wojdor.repository_rates.mock

import com.wojdor.data.RatesDto
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.RepositoryRates
import io.reactivex.Single
import kotlin.random.Random

class MockRepositoryRates : RepositoryRates {

    private val mockDate by lazy { "2018-09-06" }
    private val mockBase by lazy { "EUR" }
    private val mockMap by lazy {
        mutableMapOf<String?, Double?>().apply {
            Currency.values()
                .filterNot { it.toString() == mockBase }
                .forEach {
                    put(it.toString(), Random.Default.nextDouble(0.1, 3.0))
                }
        }
    }

    override fun getRates(baseCurrency: Currency): Single<RatesDto> =
        Single.just(RatesDto(mockBase, mockDate, mockMap))
}