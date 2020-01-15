package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.ServerRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val serverRepository: ServerRepository
) {

    fun getToken(
        login: String,
        password: String
    ): Single<List<String>> {
        return serverRepository.getAuthToken(login, password)
    }

}