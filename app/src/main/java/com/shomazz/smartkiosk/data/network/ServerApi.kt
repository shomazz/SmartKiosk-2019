package com.shomazz.smartkiosk.data.network

import com.shomazz.smartkiosk.data.network.request.AuthTokenRequestDto
import com.shomazz.smartkiosk.data.network.response.TokenDto
import com.shomazz.smartkiosk.data.network.response.UserDto
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerApi {

    companion object {
        const val BASE_URL = "http://192.168.1.67:8080"
    }

    @POST("/user/authenticate")
    fun getAuthToken(@Body authTokenRequestDto: AuthTokenRequestDto): Single<TokenDto>

    @GET("/user")
    fun getUserInfo(@Query("qr_data") userId: String): Single<UserDto>

    @POST("booths/set_print_ip")
    fun setPrinterIp(@Query("ip") ip: String,
                     @Query("boothId") kioskId: String): Completable

}