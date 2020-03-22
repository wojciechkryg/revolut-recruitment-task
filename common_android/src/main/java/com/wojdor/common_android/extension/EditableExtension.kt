package com.wojdor.common_android.extension

import android.text.Editable
import com.wojdor.common.extension.toBigDecimal

fun Editable.toBigDecimal() = this.toString().toBigDecimal()