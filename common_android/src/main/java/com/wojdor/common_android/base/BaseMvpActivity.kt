package com.wojdor.common_android.base

import android.os.Bundle
import com.wojdor.common.base.BaseContract

abstract class BaseMvpActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    BaseActivity() {

    protected abstract val presenter: P
    protected abstract val view: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttachView(view)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }
}