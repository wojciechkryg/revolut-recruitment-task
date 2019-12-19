package com.wojdor.common_android.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(model: T)
    open fun onUpdate(bundle: Bundle) {}

    fun onUpdate(payloads: List<Any>) {
        onUpdate(payloads.first() as? Bundle ?: return)
    }
}