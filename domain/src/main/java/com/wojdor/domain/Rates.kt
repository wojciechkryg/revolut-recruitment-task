package com.wojdor.domain

import com.wojdor.domain.enums.Currency

data class Rates(
    val date: String,
    val rates: Map<Currency, Double>
)