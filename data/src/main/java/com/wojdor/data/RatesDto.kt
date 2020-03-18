package com.wojdor.data

data class RatesDto(
    val baseCurrency: String?,
    val rates: Map<String?, Double?>?
)