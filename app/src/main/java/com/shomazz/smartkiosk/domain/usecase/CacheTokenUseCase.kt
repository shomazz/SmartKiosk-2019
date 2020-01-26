package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Completable
import javax.inject.Inject

class CacheTokenUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun cacheToken(token: String) : Completable{
        return prefsRepository.cacheToken(token)
    }

}