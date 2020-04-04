package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Completable
import javax.inject.Inject

class CachePrinterIpUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun cachePrinterIp(ip: String) : Completable{
        return prefsRepository.cachePrinterIp(ip)
    }

}