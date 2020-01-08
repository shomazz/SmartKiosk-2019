package com.shomazz.smartkiosk.presentation.auth

import com.shomazz.smartkiosk.util.BasePresenter
import com.shomazz.smartkiosk.util.SimpleSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthPresenter(
    override var view: AuthView
) : BasePresenter<AuthView>() {

    private val authUseCases: AuthUseCases = AuthUseCases()

    fun onLoginClick() {
        val login = view.getLogin()
        val password = view.getPassword()

        view.showProgress()
        authUseCases.getToken(login, password)
            .subscribeOn(Schedulers.newThread())
            .doOnSubscribe(::addDisposable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(AuthObserver())
    }

    private inner class AuthObserver : SimpleSingleObserver<String>() {

        override fun onSuccess(token: String) {
            super.onSuccess(token)
            view.hideProgress()
            view.onError(token)
            navigator.openMainMenu()
            //TODO("cache token")
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            view.hideProgress()
            view.onError(e.message ?: "Something goes wrong")
            //TODO("not overrided")
        }

    }

}