package com.wojdor.common.extension

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

fun BigDecimal.formatToTwoDecimalPlacesIfExist(): String =
    NumberFormat.getInstance().format(this.setScale(2, RoundingMode.HALF_UP))