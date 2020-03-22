package com.wojdor.common.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object BigDecimalFormatter {

    private const val DECIMAL_PLACES = 2
    private val locale get() = Locale.getDefault()
    private val numberFormat
        get() = (NumberFormat.getInstance(locale) as DecimalFormat).apply {
            isParseBigDecimal = true
        }

    fun formatToTwoDecimalPlacesString(number: BigDecimal): String =
        numberFormat.format(number.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP))

    fun formatToBigDecimal(text: String): BigDecimal = try {
        numberFormat.parse(text) as BigDecimal
    } catch (exception: Throwable) {
        BigDecimal.ZERO
    }
}