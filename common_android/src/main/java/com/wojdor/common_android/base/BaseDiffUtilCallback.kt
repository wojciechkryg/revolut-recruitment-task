package com.wojdor.common_android.base

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallback<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle()
        changePayload(oldItems[oldItemPosition], newItems[newItemPosition], bundle)
        return if (bundle.isEmpty) null else bundle
    }

    open fun changePayload(oldItem: T, newItem: T, bundle: Bundle) {}

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}