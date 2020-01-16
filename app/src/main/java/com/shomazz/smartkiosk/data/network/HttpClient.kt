package com.shomazz.smartkiosk.data.network

import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class HttpClient @Inject constructor(
    private val retrofit: Retrofit
) {

    fun getAuthToken(
        login: String,
        password: String
    ): Single<List<TokenDto>> {
        return retrofit
            .create(ServerApi::class.java)
            .getAuthToken(login)
    }

    fun getUserInfo(id: String): Single<List<UserDto>> {
        return retrofit
            .create(ServerApi::class.java)
            .getUserInfo(id)
    }

}