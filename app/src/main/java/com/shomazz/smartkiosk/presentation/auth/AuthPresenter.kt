package com.shomazz.smartkiosk.presentation.auth

import com.shomazz.smartkiosk.domain.usecase.CacheTokenUseCase
import com.shomazz.smartkiosk.domain.usecase.GetTokenUseCase
import com.shomazz.smartkiosk.mvp.BaseFragmentPresenter
import com.shomazz.smartkiosk.util.SimpleSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    val authUseCase: GetTokenUseCase,
    val cacheTokenUseCase: CacheTokenUseCase
) : BaseFragmentPresenter<AuthView>() {

    fun onLoginClick() {
        val login = view.getLogin()
        val password = view.getPassword()

        //Пример
        //BaseApp.settingsInteractor.setLocale(Locale.ENGLISH).subscribe()

        view.showProgress(true)
        authUseCase.getToken(login, password)
            .doOnSubscribe(::addDisposable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(AuthObserver())
    }

    private inner class AuthObserver : SimpleSingleObserver<String>() {

        override fun onSuccess(token: String) {
            super.onSuccess(token)
            view.showProgress(false)
            view.onError(token)
            navigator.openMenu()
            cacheTokenUseCase.cacheToken(token).subscribe()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            view.showProgress(false)
            view.onError(e.message ?: "Something goes wrong")
            //TODO: normal onError
        }

    }

}