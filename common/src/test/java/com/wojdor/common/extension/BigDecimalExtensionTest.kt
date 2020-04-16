package com.wojdor.common.extension

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionTest {

    @Test
    fun `When extension is used for no decimal places number then should return number without decimal places`() {
        assertEquals("12", BigDecimal(12).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for 1 decimal place number then should return 1 decimal place number`() {
        assertEquals("12,3", BigDecimal(12.3).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for 2 decimal places number then should return 2 decimal places number`() {
        assertEquals("12,34", BigDecimal(12.34).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for 3 decimal places number then should return 2 decimal places number`() {
        assertEquals("12,34", BigDecimal(12.340).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for more than 2 decimal places number then should return 2 decimal places number`() {
        assertEquals("12,34", BigDecimal(12.34443).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for 3 decimal places number which should be rounded up then should return 2 decimal places number`() {
        assertEquals("12,35", BigDecimal(12.345).formatToTwoDecimalPlacesString())
    }

    @Test
    fun `When extension is used for a lot of decimal places number then should return 2 decimal places number`() {
        assertEquals(
            "12,35", BigDecimal(12.3463244231).formatToTwoDecimalPlacesString()
        )
    }

    @Test
    fun `When extension is used for more than 2 decimal places number which should be rounded up then should return 2 decimal places number`() {
        assertEquals("12,35", BigDecimal(12.3490013).formatToTwoDecimalPlacesString())
    }
}