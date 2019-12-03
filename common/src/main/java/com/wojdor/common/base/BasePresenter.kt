package com.wojdor.common.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    override val compositeDisposable by lazy { CompositeDisposable() }
    override var view: V? = null

    override fun detachView() {
        view = null
        clearCompositeDisposable()
    }

    override fun attachView(view: V) {
        this.view = view
    }

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}