package com.wojdor.common.extension

import junit.framework.Assert.assertEquals
import org.junit.Test

class CharExtensionTest {

    @Test
    fun `When extension dot is used then should return dot character`() {
        assertEquals('.', Char.dot)
        assertEquals(46, Char.dot.toInt())
    }

    @Test
    fun `When extension comma is used then should return comma character`() {
        assertEquals(',', Char.comma)
        assertEquals(44, Char.comma.toInt())
    }

    @Test
    fun `When extension nonBreakingSpace is used then should return non breaking space character`() {
        assertEquals('\u00A0', Char.nonBreakingSpace)
        assertEquals(160, Char.nonBreakingSpace.toInt())
    }
}