package com.wojdor.domain

import com.wojdor.domain.enums.Currency
import java.math.BigDecimal

data class Rate(val currency: Currency, val rate: BigDecimal)