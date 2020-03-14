package com.wojdor.common.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    override val compositeDisposable by lazy { CompositeDisposable() }
    override var view: V? = null

    override fun onDetachView() {
        view = null
        clearCompositeDisposable()
    }

    override fun onAttachView(view: V) {
        this.view = view
    }

    protected fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}