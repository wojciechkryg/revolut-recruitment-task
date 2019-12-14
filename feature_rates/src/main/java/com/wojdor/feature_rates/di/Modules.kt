package com.wojdor.feature_rates.di

import com.wojdor.common.extension.empty
import com.wojdor.config.BaseUrl
import com.wojdor.config.Environment
import com.wojdor.feature_rates.BuildConfig
import com.wojdor.feature_rates.RatesContract
import com.wojdor.feature_rates.RatesPresenter
import com.wojdor.network.ServiceGenerator
import com.wojdor.repository_rates.BaseRatesRepository
import com.wojdor.repository_rates.mock.MockRatesRepository
import com.wojdor.repository_rates.network.NetworkRatesRepository
import com.wojdor.repository_rates.network.RatesApi
import com.wojdor.usecase_rates.BaseRatesUsecase
import com.wojdor.usecase_rates.RatesUsecase
import org.koin.dsl.module

private val networkModules = module {
    single {
        val url = when {
            isDevelop -> BaseUrl.develop
            isTest -> BaseUrl.test
            isProduction -> BaseUrl.production
            else -> String.empty
        }
        ServiceGenerator(url).createService(RatesApi::class.java)
    }
}

private val dataModules = module {
    single {
        when {
            isMock -> MockRatesRepository()
            else -> NetworkRatesRepository(get())
        }
    }
}

private val usecaseModules = module {
    single { RatesUsecase(get()) }
}

private val presenterModules = module {
    single { RatesPresenter(get()) }
}

val featureRatesModules = listOf(networkModules, dataModules, usecaseModules, presenterModules)

private val isMock
    get() = BuildConfig.FLAVOR.contains(Environment.mock)

private val isDevelop
    get() = BuildConfig.FLAVOR.contains(Environment.develop)

private val isTest
    get() = BuildConfig.FLAVOR.contains(Environment.test)

private val isProduction
    get() = BuildConfig.FLAVOR.contains(Environment.production)