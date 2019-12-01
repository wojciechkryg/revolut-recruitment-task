package com.wojdor.usecase_rates

import com.wojdor.common.extension.empty
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.BaseRatesRepository
import io.mockk.every
import io.mockk.spyk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class RatesUsecaseTest {

    private val mockRatesRepository by lazy { spyk<BaseRatesRepository>() }
    private val mockEmptyRates by lazy { Rates(String.empty, emptyMap()) }
    private val mockDate by lazy { "2018-09-06" }
    private val mockRates by lazy {
        Rates(mockDate, mutableMapOf<Currency, Double>().apply {
            Currency.values().forEachIndexed { index, currency ->
                put(currency, index.toDouble())
            }
        })
    }

    @Test
    fun `When usecase has empty Rates then should return empty Rates`() {
        every { mockRatesRepository.getRates() } returns Single.just(mockEmptyRates)
        val usecaseResult = RatesUsecase(mockRatesRepository).getRatesWithInterval().blockingFirst()
        assert(usecaseResult.rates.isEmpty())
    }

    @Test
    fun `When usecase has Rates then should return same Rates`() {
        every { mockRatesRepository.getRates() } returns Single.just(mockRates)
        val usecaseResult = RatesUsecase(mockRatesRepository).getRatesWithInterval().blockingFirst()
        assert(usecaseResult.rates.isNotEmpty())
        assertEquals(mockDate, usecaseResult.date)
        Currency.values().forEach {
            assert(usecaseResult.rates.contains(it))
        }
    }
}