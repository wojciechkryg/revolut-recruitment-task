package com.wojdor.feature_rates.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.common_android.extension.inflate
import com.wojdor.domain.Rate
import com.wojdor.feature_rates.R


class RatesAdapter : RecyclerView.Adapter<RateViewHolder>() {

    private val items = mutableListOf<Rate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val view = parent.inflate(R.layout.item_rate)
        return RateViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onBindViewHolder(
        holder: RateViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when {
            payloads.isEmpty() -> super.onBindViewHolder(holder, position, payloads)
            else -> holder.onUpdate(payloads)
        }
    }

    fun showRates(rates: List<Rate>) {
        val diffResult = DiffUtil.calculateDiff(RatesDiffUtilCallback(items, rates))
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(rates)
    }
}