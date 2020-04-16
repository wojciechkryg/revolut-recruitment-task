package com.wojdor.common_android.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToTop() {
    (this.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 1)
}