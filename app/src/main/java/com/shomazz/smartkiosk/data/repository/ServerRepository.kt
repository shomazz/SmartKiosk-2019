package com.shomazz.smartkiosk.data.repository

import com.shomazz.smartkiosk.data.network.HttpClient
import com.shomazz.smartkiosk.data.network.TokenDto
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServerRepository @Inject constructor(
    private val httpClient: HttpClient
) {

    fun getAuthToken(
        login: String,
        password: String
    ): Single<List<String>> {
        //TODO: add cache
        return httpClient.getAuthToken(login, password)
            .subscribeOn(Schedulers.io())
            .map { it.map(TokenDto::token) }
    }

}