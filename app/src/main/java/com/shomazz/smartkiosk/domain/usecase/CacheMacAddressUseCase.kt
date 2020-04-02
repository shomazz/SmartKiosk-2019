package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Completable
import javax.inject.Inject

class CacheMacAddressUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun cacheMacAddress(address: String) : Completable{
        return prefsRepository.cacheMacAddress(address)
    }

}