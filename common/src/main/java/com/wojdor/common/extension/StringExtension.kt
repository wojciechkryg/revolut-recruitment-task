package com.wojdor.common.extension

import com.wojdor.common.util.BigDecimalFormatter

private const val FIRST_INDEX = 0

val String.Companion.empty
    get() = ""

fun String?.toBigDecimal() = BigDecimalFormatter.formatToBigDecimal(this ?: String.empty)

fun String?.formatToTwoDecimalPlacesString() = toBigDecimal().formatToTwoDecimalPlacesString()

fun String.substringTo(index: Int) = when {
    index > length -> this
    index < FIRST_INDEX -> String.empty
    else -> substring(FIRST_INDEX, index)
}