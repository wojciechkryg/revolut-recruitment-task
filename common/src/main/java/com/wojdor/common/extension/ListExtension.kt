package com.wojdor.common.extension

import com.wojdor.common.interfaces.DeepCopyable

@Suppress("UNCHECKED_CAST")
fun <E : DeepCopyable> List<E>.deepCopy() = map { it.deepCopy() as E }