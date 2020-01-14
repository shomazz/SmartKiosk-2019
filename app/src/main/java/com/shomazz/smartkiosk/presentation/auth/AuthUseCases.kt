package com.shomazz.smartkiosk.presentation.auth

import com.shomazz.smartkiosk.domain.usecase.GetTokenUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCases @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) {

    fun getToken(
        login: String,
        password: String
    ): Single<String> {
        return getTokenUseCase.getToken(login, password)
    }

}