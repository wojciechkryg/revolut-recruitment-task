package com.wojdor.domain

data class Rates(
    val rates: List<Rate>
) {
    constructor() : this(emptyList())
}