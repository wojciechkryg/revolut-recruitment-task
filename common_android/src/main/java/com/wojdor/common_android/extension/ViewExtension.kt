package com.wojdor.common_android.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            ?: return
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}