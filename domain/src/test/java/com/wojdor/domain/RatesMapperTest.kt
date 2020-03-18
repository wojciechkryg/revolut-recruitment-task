package com.wojdor.domain

import com.wojdor.data.RatesDto
import com.wojdor.domain.enums.Currency
import com.wojdor.domain.mapper.RatesMapper
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class RatesMapperTest {

    @Test
    fun `When RatesDto is null then should return empty Rates`() {
        val ratesDto = null
        val rates = RatesMapper.map(ratesDto)
        assertEquals(emptyList<Rate>(), rates.rates)
    }

    @Test
    fun `When RatesDto fields are null then should return default values`() {
        val ratesDto = RatesDto(null, null)
        val rates = RatesMapper.map(ratesDto)
        assertEquals(emptyList<Rate>(), rates.rates)
    }

    @Test
    fun `When RatesDto map fields are null then should return empty map`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put(null, null)
        }
        val ratesDto = RatesDto(null, mockedCurrencyMap)
        val rates = RatesMapper.map(ratesDto)
        assertEquals(emptyList<Rate>(), rates.rates)
    }

    @Test
    fun `When RatesDto is mapped then should return proper values`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put("USD", 1.234)
        }
        val ratesDto = RatesDto("EUR", mockedCurrencyMap)
        val rates = RatesMapper.map(ratesDto)
        val expectedRatesList = mutableListOf<Rate>().apply {
            add(Rate(Currency.EUR, BigDecimal.ONE))
            add(Rate(Currency.USD, BigDecimal(1.234)))
        }
        assertEquals(expectedRatesList, rates.rates)
    }

    @Test
    fun `When RatesDto is mapped with non existing currencies then should skip non existing currencies`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put("XDD", 1.234)
            put("USD", 1.234)
            put("XXX", 1.234)
        }
        val ratesDto = RatesDto("BLE", mockedCurrencyMap)
        val rates = RatesMapper.map(ratesDto)
        val expectedRatesList = mutableListOf<Rate>().apply {
            add(Rate(Currency.USD, BigDecimal(1.234)))
        }
        assertEquals(expectedRatesList, rates.rates)
    }
}