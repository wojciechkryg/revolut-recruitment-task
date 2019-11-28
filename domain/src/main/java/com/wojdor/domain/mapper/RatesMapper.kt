package com.wojdor.domain.mapper

import com.wojdor.common.extension.empty
import com.wojdor.common.util.enumValueOrNull
import com.wojdor.data.RatesDto
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency

object RatesMapper {

    private const val DEFAULT_BASE_RATE = 1.0

    fun map(ratesDto: RatesDto?) = Rates(
        ratesDto?.date ?: String.empty,
        ratesDto?.let { mapCurrencies(it) } ?: emptyMap()
    )

    private fun mapCurrencies(ratesDto: RatesDto): Map<Currency, Double> {
        return mutableMapOf<Currency, Double>().apply {
            ratesDto.base?.let {
                put(enumValueOrNull(it) ?: return@let, DEFAULT_BASE_RATE)
            }
            ratesDto.rates?.forEach {
                val key = enumValueOrNull<Currency>(it.key ?: return@forEach) ?: return@forEach
                val value = it.value ?: return@forEach
                put(key, value)
            }
        }
    }
}