package com.shomazz.smartkiosk.data.repository

import com.shomazz.smartkiosk.data.mapper.UserMapper
import com.shomazz.smartkiosk.data.network.HttpClient
import com.shomazz.smartkiosk.data.network.response.TokenDto
import com.shomazz.smartkiosk.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServerRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val userMapper: UserMapper
) {

    fun getAuthToken(
        login: String,
        password: String
    ): Single<String> {
        return httpClient.getAuthToken(login, password)
            .subscribeOn(Schedulers.io())
            .map(TokenDto::token)
    }

    fun getUserInfo(id: String): Single<User> {
        return httpClient.getUserInfo(id)
            .subscribeOn(Schedulers.io())
            .map(userMapper::map)
    }

    fun setPrinterIp(
        ip: String,
        kioskId: String
    ): Completable {
        return httpClient.setPrinterIp(ip, kioskId)
            .subscribeOn(Schedulers.io())
    }

}