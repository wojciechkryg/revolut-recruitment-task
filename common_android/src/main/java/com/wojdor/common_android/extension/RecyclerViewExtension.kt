package com.wojdor.common_android.extension

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToTop() {
    smoothScrollToPosition(0)
}