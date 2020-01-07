package com.wojdor.common.extension

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class BigDecimalExtensionTest {

    @Before
    fun setupDefaultLocale() {
        NumberFormat.getInstance(Locale.US)
    }

    @Test
    fun `When extension is used for no decimal places number then should return number without decimal places`() {
        Assert.assertEquals("12", BigDecimal(12).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for 1 decimal place number then should return 1 decimal place number`() {
        Assert.assertEquals("12,3", BigDecimal(12.3).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for 2 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals("12,34", BigDecimal(12.34).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for 3 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals("12,34", BigDecimal(12.340).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for more than 2 decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals("12,34", BigDecimal(12.34443).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for 3 decimal places number which should be rounded up then should return 2 decimal places number`() {
        Assert.assertEquals("12,35", BigDecimal(12.345).formatToTwoDecimalPlacesIfExist())
    }

    @Test
    fun `When extension is used for a lot of decimal places number then should return 2 decimal places number`() {
        Assert.assertEquals(
            "12,35",
            BigDecimal(12.3463244231).formatToTwoDecimalPlacesIfExist()
        )
    }

    @Test
    fun `When extension is used for more than 2 decimal places number which should be rounded up then should return 2 decimal places number`() {
        Assert.assertEquals("12,35", BigDecimal(12.3490013).formatToTwoDecimalPlacesIfExist())
    }
}