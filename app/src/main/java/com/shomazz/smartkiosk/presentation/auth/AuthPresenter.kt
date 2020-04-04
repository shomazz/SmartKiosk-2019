package com.shomazz.smartkiosk.presentation.auth

import android.annotation.SuppressLint
import com.shomazz.smartkiosk.R
import com.shomazz.smartkiosk.domain.usecase.CachePrinterIpUseCase
import com.shomazz.smartkiosk.domain.usecase.CacheTokenUseCase
import com.shomazz.smartkiosk.domain.usecase.GetTokenUseCase
import com.shomazz.smartkiosk.domain.usecase.SetPrinterIpUseCase
import com.shomazz.smartkiosk.util.BasePresenter
import com.shomazz.smartkiosk.util.SimpleSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val authUseCase: GetTokenUseCase,
    private val cacheTokenUseCase: CacheTokenUseCase,
    private val cachePrinterIpUseCase: CachePrinterIpUseCase,
    private val setPrinterIpUseCase: SetPrinterIpUseCase
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
    fun onInputPrinterIpClick(ip: String) {
        // TODO: вместо "test" вставлять IMEI код устройства
        setPrinterIpUseCase.setPrinterIp(ip, "test")
            .doOnSubscribe(::addDisposable)
            .subscribe({
                cachePrinterIpUseCase.cachePrinterIp(ip)
                    .doOnSubscribe(::addDisposable)
                    .subscribe(
                        { navigator.openMenu() },
                        { view.onError(R.string.something_goes_wrong) })
            }, { view.onError(it.message ?: "") })
    }

    private inner class AuthObserver : SimpleSingleObserver<String>() {

        override fun onSuccess(token: String) {
            super.onSuccess(token)
            view.showProgress(false)
            view.showPrinterIpDialog()
            cacheTokenUseCase.cacheToken(token).subscribe()
        }

        override fun onError(e: Throwable) {
            super.onError(e)
            //401 - неверный логин или пароль
            view.showProgress(false)
            val msg = e.message
            if (msg != null) view.onError(msg)
            else view.onError(R.string.something_goes_wrong)
            //TODO: normal onError
        }

    }

}