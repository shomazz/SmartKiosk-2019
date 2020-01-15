package com.shomazz.smartkiosk.data.network

import com.google.gson.annotations.SerializedName

data class TokenDto (
    @SerializedName("name") val token: String
)