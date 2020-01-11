package com.wojdor.feature_rates.view

import android.os.Bundle
import android.view.View
import com.wojdor.common.extension.formatToTwoDecimalPlacesIfExist
import com.wojdor.common_android.base.BaseViewHolder
import com.wojdor.common_android.util.CurrencyRescources
import com.wojdor.domain.Rate
import com.wojdor.domain.enums.Currency
import kotlinx.android.synthetic.main.item_rate.view.*
import java.math.BigDecimal

class RateViewHolder(itemView: View) : BaseViewHolder<Rate>(itemView) {

    override fun onBind(model: Rate) {
        setCurrencyIcon(model.currency)
        setCurrencyLabels(model.currency)
        setRate(model.rate)
    }

    private fun setCurrencyLabels(currency: Currency) {
        itemView.itemRateCurrencyTv.text = currency.name
        itemView.itemRateCurrencyNameTv.setText(CurrencyRescources.getName(currency))
    }

    private fun setCurrencyIcon(currency: Currency) {
        itemView.itemRateCurrencyFlagIv.setImageResource(CurrencyRescources.getImage(currency))
    }

    private fun setRate(rate: BigDecimal) {
        itemView.itemRateCurrencyRateEt.setText(rate.formatToTwoDecimalPlacesIfExist())
    }

    override fun onUpdate(bundle: Bundle) {
        updateRate(bundle)
    }

    private fun updateRate(bundle: Bundle) {
        if (bundle.containsKey(RatesDiffUtilCallback.RATE_KEY)) {
            val rate =
                bundle.getSerializable(RatesDiffUtilCallback.RATE_KEY) as? BigDecimal ?: return
            setRate(rate)
        }
    }
}