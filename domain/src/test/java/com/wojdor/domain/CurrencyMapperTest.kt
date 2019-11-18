package com.wojdor.domain

import com.wojdor.common.extension.empty
import com.wojdor.data.RatesDto
import com.wojdor.domain.enums.Currency
import com.wojdor.domain.mapper.CurrencyMapper
import org.junit.Assert
import org.junit.Test

class CurrencyMapperTest {

    @Test
    fun `When RatesDto is null then should return empty Rates`() {
        val ratesDto = null
        val rates = CurrencyMapper.map(ratesDto)
        Assert.assertEquals(String.empty, rates.date)
        Assert.assertEquals(emptyMap<Currency, Double>(), rates.rates)
    }

    @Test
    fun `When RatesDto fields are null then should return default values`() {
        val ratesDto = RatesDto(null, null, null)
        val rates = CurrencyMapper.map(ratesDto)
        Assert.assertEquals(String.empty, rates.date)
        Assert.assertEquals(emptyMap<Currency, Double>(), rates.rates)
    }

    @Test
    fun `When RatesDto map fields are null then should return empty map`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put(null, null)
        }
        val ratesDto = RatesDto(null, null, mockedCurrencyMap)
        val rates = CurrencyMapper.map(ratesDto)
        Assert.assertEquals(emptyMap<Currency, Double>(), rates.rates)
    }

    @Test
    fun `When RatesDto is mapped then should return proper values`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put("USD", 1.234)
        }
        val mockDate = "2018-09-06"
        val ratesDto = RatesDto("EUR", mockDate, mockedCurrencyMap)
        val rates = CurrencyMapper.map(ratesDto)
        val expectedCurrencyMap = mutableMapOf<Currency, Double>().apply {
            put(Currency.EUR, 1.0)
            put(Currency.USD, 1.234)
        }
        Assert.assertEquals(mockDate, rates.date)
        Assert.assertEquals(expectedCurrencyMap, rates.rates)
    }

    @Test
    fun `When RatesDto is mapped with non existing currencies then should skip non existing currencies`() {
        val mockedCurrencyMap = mutableMapOf<String?, Double?>().apply {
            put("XDD", 1.234)
            put("USD", 1.234)
            put("XXX", 1.234)
        }
        val ratesDto = RatesDto("BLE", null, mockedCurrencyMap)
        val rates = CurrencyMapper.map(ratesDto)
        val expectedCurrencyMap = mutableMapOf<Currency, Double>().apply {
            put(Currency.USD, 1.234)
        }
        Assert.assertEquals(expectedCurrencyMap, rates.rates)
    }
}