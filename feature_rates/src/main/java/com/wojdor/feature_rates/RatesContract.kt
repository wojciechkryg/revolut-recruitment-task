package com.wojdor.feature_rates

import com.wojdor.common.base.BaseContract
import com.wojdor.domain.Rates
import com.wojdor.usecase_rates.BaseRatesUsecase

interface RatesContract {

    interface View : BaseContract.View {
        fun showRates(rates: List<Rates>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        val ratesUsecase: BaseRatesUsecase
    }
}