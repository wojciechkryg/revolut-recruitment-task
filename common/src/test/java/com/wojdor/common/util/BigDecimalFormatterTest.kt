package com.wojdor.common.util

import com.wojdor.common.extension.empty
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalFormatterTest {

    @Test
    fun `When formatter is used for no decimal places number then should return number without decimal places`() {
        Assert.assertEquals(
            "12",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12))
        )
    }

    @Test
    fun `When formatter is used for 1 decimal place number then should return 1 decimal place number`() {
        Assert.assertEquals(
            "12,3",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.3))
        )
    }

    @Test
    fun `When formatter is used for 2 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,34",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.34))
        )
    }

    @Test
    fun `When formatter is used for 3 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,34",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.340))
        )
    }

    @Test
    fun `When formatter is used for more than 2 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,34",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.34443))
        )
    }

    @Test
    fun `When formatter is used for 3 decimal places number which should be rounded up then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,35",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.345))
        )
    }

    @Test
    fun `When formatter is used for a lot of decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,35",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.3463244231))
        )
    }

    @Test
    fun `When formatter is used for more than 2 decimal places number which should be rounded up then should return 2 decimal places number`() {
        assertEquals(
            "12,35",
            BigDecimalFormatter.formatToTwoDecimalPlacesString(BigDecimal(12.3490013))
        )
    }

    @Test
    fun `When formatter is used for text then should return number`() {
        assertEquals(
            BigDecimal(12.35).setScale(2, RoundingMode.HALF_UP),
            BigDecimalFormatter.formatToBigDecimal("12,35")
        )
    }

    @Test
    fun `When formatter is used for empty text then should return zero`() {
        assertEquals(
            BigDecimal.ZERO, BigDecimalFormatter.formatToBigDecimal(String.empty)
        )
    }
}