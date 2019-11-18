package com.wojdor.common.extension

import com.wojdor.common.util.enumValueOrNull
import org.junit.Assert
import org.junit.Test

class EnumUtilTest {

    @Test
    fun `When String as enum exists then should return enum`() {
        Assert.assertEquals(TestEnum.A, enumValueOrNull<TestEnum>("A"))
    }

    @Test
    fun `When String as enum does not exist then should return null`() {
        Assert.assertEquals(null, enumValueOrNull<TestEnum>("B"))
    }

    enum class TestEnum { A }
}