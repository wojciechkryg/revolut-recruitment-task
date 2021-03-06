package com.wojdor.feature_rates.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wojdor.common_android.base.BaseMvpActivity
import com.wojdor.common_android.extension.gone
import com.wojdor.common_android.extension.scrollToTop
import com.wojdor.common_android.extension.visible
import com.wojdor.domain.Rates
import com.wojdor.feature_rates.R
import com.wojdor.feature_rates.RatesContract
import kotlinx.android.synthetic.main.activity_rates.*
import org.koin.android.ext.android.inject

class RatesActivity : BaseMvpActivity<RatesContract.View, RatesContract.Presenter>(),
    RatesContract.View {

    override val view = this
    override val presenter: RatesContract.Presenter by inject()
    private lateinit var ratesAdapter: RatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)
        title = getString(R.string.rates)
        setupRatesList()
    }

    private fun setupRatesList() {
        with(ratesRatesRv) {
            layoutManager = LinearLayoutManager(this@RatesActivity)
            adapter = createRatesAdapter().also { ratesAdapter = it }
        }
    }

    private fun createRatesAdapter() = RatesAdapter().apply {
        onClick = {
            presenter.setCurrencyAsChosen(it.currency)
            ratesRatesRv.scrollToTop()
        }
        onUserInput = {
            presenter.setRateMultiplier(it)
        }
    }

    override fun getRates() = ratesAdapter.items

    override fun showRates(rates: Rates) {
        ratesAdapter.showRates(rates.rates)
    }

    override fun showFetchRatesError(error: Throwable) {
        ratesRatesRv.gone()
        ratesNoDataTv.visible()
    }
}