package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCachedTokenUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun getToken(): Single<String?> {
        return prefsRepository.getToken()
    }

}