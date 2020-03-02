package com.wojdor.domain

import com.wojdor.common.extension.empty

data class Rates(
    val date: String,
    val rates: List<Rate>
) {
    constructor() : this(String.empty, emptyList())
}