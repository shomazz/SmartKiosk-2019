package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.ServerRepository
import com.shomazz.smartkiosk.domain.model.User
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val serverRepository: ServerRepository
) {

    fun getUser(id: String): Single<User> {
        return serverRepository.getUserInfo(id)
    }

}