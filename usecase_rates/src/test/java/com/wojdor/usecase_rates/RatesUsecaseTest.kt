package com.wojdor.usecase_rates

import com.wojdor.common.extension.empty
import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.repository_rates.BaseRatesRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class RatesUsecaseTest {

    private val mockRatesRepository by lazy { mockk<BaseRatesRepository>() }
    private val mockEmptyRates by lazy { Rates(String.empty, emptyList()) }
    private val mockDate by lazy { "2018-09-06" }
    private val mockRates by lazy {
        Rates(mockDate, mutableListOf<Rate>().apply {
            Currency.values().forEachIndexed { index, currency ->
                add(Rate(currency, BigDecimal(index)))
            }
        })
    }

    @Test
    fun `When usecase has empty Rates then should return empty Rates`() {
        every { mockRatesRepository.getRates(any()) } returns Single.just(mockEmptyRates)
        val usecaseResult = RatesUsecase(mockRatesRepository).getRatesWithInterval(Currency.EUR).blockingFirst()
        assert(usecaseResult.rates.isEmpty())
    }

    @Test
    fun `When usecase has Rates then should return same Rates`() {
        every { mockRatesRepository.getRates(any()) } returns Single.just(mockRates)
        val usecaseResult = RatesUsecase(mockRatesRepository).getRatesWithInterval(Currency.EUR).blockingFirst()
        assert(usecaseResult.rates.isNotEmpty())
        assertEquals(mockDate, usecaseResult.date)
        Currency.values().forEach { currency ->
            assert(usecaseResult.rates.any { it.currency == currency })
        }
    }
}