package com.wojdor.common.util

inline fun <reified T : Enum<*>> enumValueOrNull(name: String): T? =
    T::class.java.enumConstants.firstOrNull { it.name == name }