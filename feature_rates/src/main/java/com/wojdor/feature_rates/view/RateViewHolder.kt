package com.wojdor.feature_rates.view

import android.view.View
import com.wojdor.common_android.base.BaseViewHolder
import com.wojdor.common_android.util.CurrencyRescources
import com.wojdor.domain.Rate
import kotlinx.android.synthetic.main.item_rate.view.*

class RateViewHolder(itemView: View) : BaseViewHolder<Rate>(itemView) {

    override fun onBind(model: Rate) {
        itemView.itemRateCurrencyFlagIv.setImageResource(CurrencyRescources.getImage(model.currency))
    }
}