package com.wojdor.feature_rates

import com.wojdor.common.base.BasePresenter
import com.wojdor.common.extension.makeFirst
import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.usecase_rates.BaseRatesUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

class RatesPresenter(override val ratesUsecase: BaseRatesUsecase) :
    BasePresenter<RatesContract.View>(), RatesContract.Presenter {

    private val ratesOrder = mutableListOf<Currency>()
    private var latestOrderedRates = Rates()

    override fun onAttachView(view: RatesContract.View) {
        super.onAttachView(view)
        fetchRates()
    }

    override fun fetchRates() {
        ratesUsecase.getRatesWithInterval()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fillFirstTimeRatesOrder(it)
                onRatesFetched(it)
            }, {
                view?.showFetchRatesError(it)
            })
            .addTo(compositeDisposable)
    }

    private fun fillFirstTimeRatesOrder(rates: Rates) {
        if (ratesOrder.isEmpty()) {
            rates.rates.forEach { ratesOrder.add(it.currency) }
        }
    }

    private fun onRatesFetched(rates: Rates) {
        latestOrderedRates = sortRatesByOrder(rates)
        view?.showRates(latestOrderedRates)
    }

    private fun sortRatesByOrder(rates: Rates) = Rates(rates.date, sortRatesByOrder(rates.rates))

    private fun sortRatesByOrder(rates: List<Rate>) = mutableListOf<Rate>().apply {
        ratesOrder.forEach { currency ->
            add(Rate(currency, rates.find { it.currency == currency }?.rate ?: BigDecimal.ZERO))
        }
    }

    override fun setCurrencyAsChosen(currency: Currency) {
        ratesOrder.makeFirst(currency)
        onRatesFetched(latestOrderedRates)
    }
}

