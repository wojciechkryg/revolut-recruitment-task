package com.wojdor.common.extension

import com.wojdor.common.util.BigDecimalFormatter
import java.math.BigDecimal

fun BigDecimal.formatToTwoDecimalPlacesString(): String =
    BigDecimalFormatter.formatToTwoDecimalPlacesString(this)