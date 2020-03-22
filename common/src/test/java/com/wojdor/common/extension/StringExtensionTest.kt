package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class StringExtensionTest {

    @Test
    fun `When extension empty is used then should return empty String`() {
        assertEquals("", String.empty)
    }

    @Test
    fun `When extension is used for text then should return number`() {
        assertEquals(BigDecimal(12.35).setScale(2, RoundingMode.HALF_UP), "12,35".toBigDecimal())
    }

    @Test
    fun `When extension is used for empty text then should return zero`() {
        assertEquals(BigDecimal.ZERO, String.empty.toBigDecimal())
    }
}