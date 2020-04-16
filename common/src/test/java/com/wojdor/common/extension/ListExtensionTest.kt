package com.wojdor.common.extension

import com.wojdor.common.interfaces.DeepCopyable
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ListExtensionTest {

    private val list by lazy { List(10) { mockk<DeepCopyable>(relaxed = true) } }

    @Test
    fun `When extension deepCopy is used then should invoke deepCopy from all elements`() {
        list.deepCopy()
        list.forEach { verify { it.deepCopy() } }
    }

    @Test
    fun `When extension deepCopy is used then should create new list with different reference`() {
        val copiedList = list.deepCopy()
        assert(copiedList !== list)
    }

    @Test
    fun `When extension deepCopy is used then should create new list with same size`() {
        val copiedList = list.deepCopy()
        assert(list.size == copiedList.size)
    }
}