package com.wojdor.domain

import com.wojdor.common.extension.deepCopy
import com.wojdor.common.interfaces.DeepCopyable

data class Rates(
    val rates: List<Rate>
) : DeepCopyable {

    constructor() : this(emptyList())

    override fun deepCopy() = Rates(rates.deepCopy())
}