package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.AuthRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun getToken(login: String, password: String): Single<String> {
        return authRepository.getAuthToken(login, password)
    }

}