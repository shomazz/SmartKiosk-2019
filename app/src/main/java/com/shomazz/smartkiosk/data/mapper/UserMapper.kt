package com.shomazz.smartkiosk.data.mapper

import com.shomazz.smartkiosk.data.network.UserDto
import com.shomazz.smartkiosk.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(userDto: UserDto): User {
        return User(
            name = userDto.name,
            organization = userDto.organization,
            payload = userDto.payload
        )
    }

}