package com.wojdor.domain.mapper

import com.wojdor.common.extension.empty
import com.wojdor.common.util.enumValueOrNull
import com.wojdor.data.RatesDto
import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency

object RatesMapper {

    private const val DEFAULT_BASE_RATE = 1.0

    fun map(ratesDto: RatesDto?) = Rates(
        ratesDto?.date ?: String.empty,
        ratesDto?.let { mapRates(it) } ?: emptyList()
    )

    private fun mapRates(ratesDto: RatesDto): List<Rate> {
        return mutableListOf<Rate>().apply {
            ratesDto.base?.let {
                add(Rate(enumValueOrNull(it) ?: return@let, DEFAULT_BASE_RATE))
            }
            ratesDto.rates?.forEach {
                val key = enumValueOrNull<Currency>(it.key ?: return@forEach) ?: return@forEach
                val value = it.value ?: return@forEach
                add(Rate(key, value))
            }
        }
    }
}