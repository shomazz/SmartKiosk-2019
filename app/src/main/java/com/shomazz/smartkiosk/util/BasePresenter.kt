package com.shomazz.smartkiosk.util

import com.shomazz.smartkiosk.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : BaseView> {

    lateinit var navigator: Navigator
    lateinit var view: T

    private val disposables = CompositeDisposable()
    private val disposableLock = Any()

    fun attach(view: T) {
        this.view = view
    }

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

}