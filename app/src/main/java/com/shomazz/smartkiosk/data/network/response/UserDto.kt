package com.shomazz.smartkiosk.data.network.response

import com.google.gson.annotations.SerializedName

data class UserDto (
    @SerializedName("userName") val name: String,
    @SerializedName("userOrganization") val organization: String,
    @SerializedName("userPayload") val payload: String
)