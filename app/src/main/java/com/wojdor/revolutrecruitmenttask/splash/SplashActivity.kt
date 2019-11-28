package com.wojdor.revolutrecruitmenttask.splash

import android.content.Intent
import android.os.Bundle
import com.wojdor.common_android.base.BaseActivity
import com.wojdor.feature_rates.view.RatesActivity
import com.wojdor.revolutrecruitmenttask.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, RatesActivity::class.java))
        finish()
    }
}