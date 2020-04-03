package com.wojdor.common.extension

import org.junit.Assert
import org.junit.Test

class IntExtensionTest {
    
    @Test
    fun `When extension zero is used then should return 0 Int`() {
        Assert.assertEquals(0, Int.zero)
    }
}