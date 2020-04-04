package com.shomazz.smartkiosk.data.network.response

import com.google.gson.annotations.SerializedName

data class TokenDto(
    @SerializedName("token") val token: String
)