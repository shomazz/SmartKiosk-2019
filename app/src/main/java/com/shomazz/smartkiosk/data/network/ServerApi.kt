package com.shomazz.smartkiosk.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {

    @GET("/rest/v2/alpha")
    fun getInfo(@Query("codes") code: String): Single<List<TokenDto>>

}