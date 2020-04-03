package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class StringExtensionTest {

    private val mockText by lazy { "123,45" }

    @Test
    fun `When extension empty is used then should return empty String`() {
        assertEquals("", String.empty)
    }

    @Test
    fun `When extension toBigDecimal is used for text then should return number`() {
        assertEquals(BigDecimal(12.35).setScale(2, RoundingMode.HALF_UP), "12,35".toBigDecimal())
    }

    @Test
    fun `When extension toBigDecimal is used for empty text then should return zero`() {
        assertEquals(BigDecimal.ZERO, String.empty.toBigDecimal())
    }

    @Test
    fun `When extension substringTo is used for text then should return proper substring`() {
        assertEquals("123", mockText.substringTo(3))
    }

    @Test
    fun `When extension substringTo is used for text with less characters then should return full text`() {
        assertEquals(mockText, mockText.substringTo(12))
    }

    @Test
    fun `When extension substringTo is used with minus index then should return empty text`() {
        assertEquals(String.empty, mockText.substringTo(-1))
    }
}