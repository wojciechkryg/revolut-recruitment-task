package com.wojdor.common_android.base

import android.os.Bundle
import android.os.PersistableBundle
import com.wojdor.common.base.BaseContract

abstract class BaseMvpActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    BaseActivity() {

    protected abstract val presenter: P
    protected abstract val view: V

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        presenter.attachView(view)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}