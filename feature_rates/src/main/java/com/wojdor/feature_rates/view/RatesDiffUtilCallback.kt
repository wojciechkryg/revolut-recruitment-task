package com.wojdor.feature_rates.view

import com.wojdor.common_android.base.BaseDiffUtilCallback
import com.wojdor.domain.Rate

class RatesDiffUtilCallback(oldItems: List<Rate>, newItems: List<Rate>) :
    BaseDiffUtilCallback<Rate>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: Rate, newItem: Rate) =
        oldItem.currency == newItem.currency

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate) = oldItem == newItem
}