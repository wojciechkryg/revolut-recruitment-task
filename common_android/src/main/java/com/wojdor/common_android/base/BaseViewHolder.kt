package com.wojdor.common_android.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun onUpdate(bundle: Bundle) {}

    fun onUpdate(payloads: List<Any>) {
        payloads.forEach {
            onUpdate(it)
        }
    }

    private fun onUpdate(payload: Any) {
        onUpdate(payload as? Bundle ?: return)
    }
}