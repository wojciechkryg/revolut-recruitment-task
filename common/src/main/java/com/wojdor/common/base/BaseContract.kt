package com.wojdor.common.base

import io.reactivex.disposables.CompositeDisposable

interface BaseContract {

    interface View

    interface Presenter<V : View> {

        val compositeDisposable: CompositeDisposable
        var view: V?
        fun onAttachView(view: V)
        fun onDetachView()
    }
}