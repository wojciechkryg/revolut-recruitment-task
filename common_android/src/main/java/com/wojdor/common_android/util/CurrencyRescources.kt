package com.wojdor.common_android.util

import com.wojdor.common_android.R
import com.wojdor.domain.enums.Currency
import com.wojdor.domain.enums.Currency.*

object CurrencyRescources {

    fun getImage(currency: Currency) = when (currency) {
        AUD -> R.drawable.ic_australia
        BGN -> R.drawable.ic_bulgaria
        BRL -> R.drawable.ic_brazil
        CAD -> R.drawable.ic_canada
        CHF -> R.drawable.ic_switzerland
        CNY -> R.drawable.ic_china
        CZK -> R.drawable.ic_czech_republic
        DKK -> R.drawable.ic_denmark
        EUR -> R.drawable.ic_european_union
        GBP -> R.drawable.ic_united_kingdom
        HKD -> R.drawable.ic_hong_kong
        HRK -> R.drawable.ic_croatia
        HUF -> R.drawable.ic_hungary
        IDR -> R.drawable.ic_indonesia
        ILS -> R.drawable.ic_israel
        INR -> R.drawable.ic_india
        ISK -> R.drawable.ic_iceland
        JPY -> R.drawable.ic_japan
        KRW -> R.drawable.ic_south_korea
        MXN -> R.drawable.ic_mexico
        MYR -> R.drawable.ic_malaysia
        NOK -> R.drawable.ic_norway
        NZD -> R.drawable.ic_new_zealand
        PHP -> R.drawable.ic_philippines
        PLN -> R.drawable.ic_poland
        RON -> R.drawable.ic_romania
        RUB -> R.drawable.ic_russia
        SEK -> R.drawable.ic_sweden
        SGD -> R.drawable.ic_singapore
        THB -> R.drawable.ic_thailand
        TRY -> R.drawable.ic_turkey
        USD -> R.drawable.ic_united_states_of_america
        ZAR -> R.drawable.ic_south_africa
    }

    fun getName(currency: Currency) = when (currency) {
        AUD -> R.string.aud_currency
        BGN -> R.string.bgn_currency
        BRL -> R.string.brl_currency
        CAD -> R.string.cad_currency
        CHF -> R.string.chf_currency
        CNY -> R.string.cny_currency
        CZK -> R.string.czk_currency
        DKK -> R.string.dkk_currency
        EUR -> R.string.eur_currency
        GBP -> R.string.gbp_currency
        HKD -> R.string.hkd_currency
        HRK -> R.string.hrk_currency
        HUF -> R.string.huf_currency
        IDR -> R.string.idr_currency
        ILS -> R.string.ils_currency
        INR -> R.string.inr_currency
        ISK -> R.string.isk_currency
        JPY -> R.string.jpy_currency
        KRW -> R.string.krw_currency
        MXN -> R.string.mxn_currency
        MYR -> R.string.myr_currency
        NOK -> R.string.nok_currency
        NZD -> R.string.nzd_currency
        PHP -> R.string.php_currency
        PLN -> R.string.pln_currency
        RON -> R.string.ron_currency
        RUB -> R.string.rub_currency
        SEK -> R.string.sek_currency
        SGD -> R.string.sgd_currency
        THB -> R.string.thb_currency
        TRY -> R.string.try_currency
        USD -> R.string.usd_currency
        ZAR -> R.string.zar_currency
    }
}