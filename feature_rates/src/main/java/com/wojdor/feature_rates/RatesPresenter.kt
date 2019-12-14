package com.wojdor.feature_rates

import com.wojdor.common.base.BasePresenter
import com.wojdor.usecase_rates.BaseRatesUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class RatesPresenter(override val ratesUsecase: BaseRatesUsecase) :
    BasePresenter<RatesContract.View>(), RatesContract.Presenter {

    override fun onAttachView(view: RatesContract.View) {
        super.onAttachView(view)
        fetchRates()
    }

    override fun fetchRates() {
        ratesUsecase.getRatesWithInterval()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showRates(it)
            }, {
                view?.showFetchRatesError(it)
            })
            .addTo(compositeDisposable)
    }
}

