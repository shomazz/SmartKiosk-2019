package com.shomazz.smartkiosk.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : BaseView> {

    lateinit var view: T

    private val disposables = CompositeDisposable()
    private val disposableLock = Any()

    fun clearDisposables() {
        synchronized(disposableLock) {
            disposables.clear()
        }
    }

    fun addDisposable(disposable: Disposable) {
        synchronized(disposableLock) {
            disposables.add(disposable)
        }
    }

    fun onStop() {
        clearDisposables()
    }

    fun attach(view: T) {
        this.view = view
    }

}