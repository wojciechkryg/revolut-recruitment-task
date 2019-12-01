package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `When extension empty is used then should return empty String`() {
        assertEquals("", String.empty)
    }
}