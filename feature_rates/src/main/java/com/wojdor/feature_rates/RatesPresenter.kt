package com.wojdor.feature_rates

import com.wojdor.common.base.BasePresenter
import com.wojdor.usecase_rates.BaseRatesUsecase

class RatesPresenter(override val ratesUsecase: BaseRatesUsecase) :
    BasePresenter<RatesContract.View>(), RatesContract.Presenter

