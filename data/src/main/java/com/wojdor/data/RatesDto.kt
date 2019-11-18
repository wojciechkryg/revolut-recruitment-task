package com.wojdor.data

data class RatesDto(
    val base: String?,
    val date: String?,
    val rates: Map<String?, Double?>?
)