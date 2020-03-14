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

    fun onBind(model: Rate, onClick: (Rate) -> Unit) {
        setCurrencyIcon(model.currency)
        setCurrencyLabels(model.currency)
        setRate(model.rate)
        setOnClick(model, onClick)
    }

    private fun setCurrencyLabels(currency: Currency) {
        with(itemView) {
            itemRateCurrencyTv.text = currency.name
            itemRateCurrencyNameTv.setText(CurrencyRescources.getName(currency))
        }
    }

    private fun setCurrencyIcon(currency: Currency) {
        itemView.itemRateCurrencyFlagIv.setImageResource(CurrencyRescources.getImage(currency))
    }

    private fun setRate(rate: BigDecimal) {
        itemView.itemRateCurrencyRateEt.setText(rate.formatToTwoDecimalPlacesIfExist())
    }

    private fun setOnClick(model: Rate, onClick: (Rate) -> Unit) {
        with(itemView) {
            setOnClickListener {
                onClick(model)
                itemRateCurrencyRateEt.requestFocus()
            }
            itemRateCurrencyRateEt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    onClick(model)
                }
            }
        }
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