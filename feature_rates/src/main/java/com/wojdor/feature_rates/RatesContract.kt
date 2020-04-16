package com.wojdor.feature_rates

import com.wojdor.common.base.BaseContract
import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.usecase_rates.BaseRatesUsecase
import java.math.BigDecimal

interface RatesContract {

    interface View : BaseContract.View {
        fun getRates(): List<Rate>
        fun showRates(rates: Rates)
        fun showFetchRatesError(error: Throwable)
    }

    interface Presenter : BaseContract.Presenter<View> {
        val ratesUsecase: BaseRatesUsecase
        fun startFetchingRates()
        fun setCurrencyAsChosen(currency: Currency)
        fun setRateMultiplier(multiplier: BigDecimal)
    }
}