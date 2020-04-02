package com.shomazz.smartkiosk.presentation.auth

import android.annotation.SuppressLint
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.domain.usecase.CacheMacAddressUseCase
import com.shomazz.smartkiosk.domain.usecase.CacheTokenUseCase
import com.shomazz.smartkiosk.domain.usecase.GetTokenUseCase
import com.shomazz.smartkiosk.util.BasePresenter
import com.shomazz.smartkiosk.util.SimpleSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authUseCase: GetTokenUseCase,
    private val cacheTokenUseCase: CacheTokenUseCase,
    private val cacheMacAddressUseCase: CacheMacAddressUseCase
) : BasePresenter<AuthView>() {

    fun onLoginClick() {
        val login = view.getLogin()
        val password = view.getPassword()

        view.showProgress(true)
        authUseCase.getToken(login, password)
            .doOnSubscribe(::addDisposable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(AuthObserver())
    }

    @SuppressLint("CheckResult")
    fun onInputMacAddressClick(address: String) {
        cacheMacAddressUseCase.cacheMacAddress(address)
            .doOnSubscribe(::addDisposable)
            .subscribe(
                { navigator.openMenu() },
                { view.onError(R.string.something_goes_wrong) }
            )
    }

    private inner class AuthObserver : SimpleSingleObserver<String>() {

        override fun onSuccess(token: String) {
            super.onSuccess(token)
            view.showProgress(false)
            view.showMacAddressDialog()
            cacheTokenUseCase.cacheToken(token).subscribe()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            view.showProgress(false)
            val msg = e.message
            if (msg != null) view.onError(msg)
            else view.onError(R.string.something_goes_wrong)
            //TODO: normal onError
        }

    }

}