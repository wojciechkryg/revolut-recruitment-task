package com.wojdor.feature_rates

import com.wojdor.domain.Rate
import com.wojdor.domain.Rates
import com.wojdor.domain.enums.Currency
import com.wojdor.usecase_rates.BaseRatesUsecase
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class RatesPresenterTest {

    private val mockRatesUsecase by lazy { mockk<BaseRatesUsecase>() }
    private val mockRatesView by lazy { spyk<RatesContract.View>() }
    private val ratesPresenter by lazy { RatesPresenter(mockRatesUsecase) }
    private val mockDate by lazy { "2018-09-06" }
    private val mockRates by lazy {
        Rates(mockDate, mutableListOf<Rate>().apply {
            Currency.values().forEachIndexed { index, currency ->
                add(Rate(currency, BigDecimal(index)))
            }
        })
    }

    @Before
    fun setupTests() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `When presenter has view attached then should fetch and show rates`() {
        every { mockRatesUsecase.getRatesWithInterval() } returns Observable.fromArray(mockRates)
        ratesPresenter.onAttachView(mockRatesView)
        verify { mockRatesView.showRates(any()) }
    }

    @Test
    fun `When presenter has view attached and fetch rates throw error then should show error message`() {
        every { mockRatesUsecase.getRatesWithInterval() } returns Observable.error(Throwable())
        ratesPresenter.onAttachView(mockRatesView)
        verify { mockRatesView.showFetchRatesError(any()) }
    }

    @Test
    fun `When presenter has currency set as chosen then should show rates in new order`() {
        ratesPresenter.view = mockRatesView
        ratesPresenter.setCurrencyAsChosen(Currency.USD)
        verify { mockRatesView.showRates(any()) }
    }
}