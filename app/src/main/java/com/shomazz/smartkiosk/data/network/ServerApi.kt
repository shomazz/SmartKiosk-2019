package com.shomazz.smartkiosk.data.network

import io.reactivex.Single
import retrofit2.http.*

interface ServerApi {

    @FormUrlEncoded
    @POST("/auth")
    fun getAuthToken(@Field("username") login: String): Single<TokenDto>

    @FormUrlEncoded
    @POST("/user")
    fun getUserInfo(@Field("userId") userId: String): Single<UserDto>

}