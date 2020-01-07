package com.shomazz.smartkiosk.presentation.auth

import com.shomazz.smartkiosk.domain.usecase.GetTokenUseCase
import io.reactivex.Single

class AuthUseCases {

    private val getTokenUseCase = GetTokenUseCase()

    fun getToken(
        login: String,
        password: String
    ): Single<String> {
        return getTokenUseCase.getToken(login, password)
    }

}