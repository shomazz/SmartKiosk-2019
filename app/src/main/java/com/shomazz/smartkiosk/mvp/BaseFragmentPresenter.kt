package com.shomazz.smartkiosk.mvp

import com.shomazz.smartkiosk.Navigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragmentPresenter<T : BaseView> : BasePresenter<T>() {
    lateinit var navigator: Navigator
}