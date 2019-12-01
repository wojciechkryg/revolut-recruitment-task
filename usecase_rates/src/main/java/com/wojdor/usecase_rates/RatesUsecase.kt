package com.wojdor.usecase_rates

import com.wojdor.domain.Rates
import com.wojdor.repository_rates.BaseRatesRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RatesUsecase(private val ratesRepository: BaseRatesRepository) : BaseRatesUsecase {

    override fun getRatesWithInterval(seconds: Long): Observable<Rates> =
        Observable.interval(seconds, TimeUnit.SECONDS)
            .startWith(START_VALUE)
            .flatMap { ratesRepository.getRates().toObservable() }

    companion object {
        private const val START_VALUE = 1L
    }
}