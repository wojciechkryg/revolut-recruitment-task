package com.wojdor.feature_rates.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wojdor.domain.Rate

class RatesAdapter : RecyclerView.Adapter<RatesViewHolder>() {

    private val items = emptyList<Rate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showRates(rates: List<Rate>) {
        // TODO: Add rates and refresh view with DiffUtil
    }
}