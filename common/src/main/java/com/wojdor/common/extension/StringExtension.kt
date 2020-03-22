package com.wojdor.common.extension

import com.wojdor.common.util.BigDecimalFormatter

val String.Companion.empty
    get() = ""

fun String.toBigDecimal() = BigDecimalFormatter.formatToBigDecimal(this)