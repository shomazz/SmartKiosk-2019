package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCachedMacAddressUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun getMacAddress(): Single<String> {
        return prefsRepository.getMacAddress()
    }

}