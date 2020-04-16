package com.wojdor.domain

import com.wojdor.common.interfaces.DeepCopyable
import com.wojdor.domain.enums.Currency
import java.math.BigDecimal

data class Rate(val currency: Currency, var rate: BigDecimal) : DeepCopyable {

    override fun deepCopy() = copy()
}