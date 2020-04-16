package com.wojdor.common.interfaces

interface DeepCopyable {
    fun deepCopy(): DeepCopyable
}