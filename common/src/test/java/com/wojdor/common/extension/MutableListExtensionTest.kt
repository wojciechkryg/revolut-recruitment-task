package com.wojdor.common.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class MutableListExtensionTest {

    @Test
    fun `When extension addFirst is used then should add element as first`() {
        val list = mutableListOf(1, 2, 3)
        list.addFirst(0)
        assertEquals(0, list.first())
    }

    @Test
    fun `When extension makeFirst is used then should switch element to first`() {
        val list = mutableListOf(1, 2, 3)
        list.makeFirst(3)
        assertEquals(3, list.first())
        assertEquals(3, list.size)
    }

    @Test
    fun `When extension makeFirst is used without that element then should add element as first`() {
        val list = mutableListOf(1, 2, 3)
        list.makeFirst(0)
        assertEquals(0, list.first())
        assertEquals(4, list.size)
    }
}