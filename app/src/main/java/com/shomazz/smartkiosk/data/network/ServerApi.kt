package com.shomazz.smartkiosk.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @GET("/rest/v2/alpha")
    fun getAuthToken(@Query("codes") code: String): Single<List<TokenDto>>

    @GET("rest/v2/capital/{capital}")
    fun getUserInfo(@Path("capital") capital: String): Single<List<UserDto>>

}