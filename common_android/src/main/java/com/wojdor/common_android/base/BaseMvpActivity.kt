package com.wojdor.common_android.base

import com.wojdor.common.base.BaseContract

abstract class BaseMvpActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    BaseActivity() {

    protected abstract val presenter: P
    protected abstract val view: V

    override fun onStart() {
        super.onStart()
        presenter.onAttachView(view)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }
}