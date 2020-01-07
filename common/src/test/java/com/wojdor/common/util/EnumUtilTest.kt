package com.wojdor.common.util

import org.junit.Assert.assertEquals
import org.junit.Test

class EnumUtilTest {

    @Test
    fun `When String as enum exists then should return enum`() {
        assertEquals(TestEnum.A, enumValueOrNull<TestEnum>("A"))
    }

    @Test
    fun `When String as enum does not exist then should return null`() {
        assertEquals(null, enumValueOrNull<TestEnum>("B"))
    }

    enum class TestEnum { A }
}