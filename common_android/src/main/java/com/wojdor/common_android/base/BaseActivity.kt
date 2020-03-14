package com.wojdor.common_android.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.wojdor.common_android.R

abstract class BaseActivity : AppCompatActivity() {

    fun showError(resId: Int) {
        showError(getString(resId))
    }

    fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).apply {
            view.setBackgroundColor(ContextCompat.getColor(this@BaseActivity, R.color.error))
            show()
        }
    }
}