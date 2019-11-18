package com.wojdor.common.extension

import org.junit.Assert
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `When extension empty is used then should return empty String`() {
        Assert.assertEquals("", String.empty)
    }
}