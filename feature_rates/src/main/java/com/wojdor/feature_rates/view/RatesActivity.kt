package com.wojdor.feature_rates.view

import android.os.Bundle
import com.wojdor.common_android.base.BaseMvpActivity
import com.wojdor.domain.Rates
import com.wojdor.feature_rates.R
import com.wojdor.feature_rates.RatesContract
import org.koin.android.ext.android.inject

class RatesActivity : BaseMvpActivity<RatesContract.View, RatesContract.Presenter>(),
    RatesContract.View {

    override val view = this
    override val presenter: RatesContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)
        title = getString(R.string.rates)
    }

    override fun showRates(rates: Rates) {
        // TODO: Show rates
    }

    override fun showFetchRatesError(error: Throwable) {
        // TODO: Show error
    }
}