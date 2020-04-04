package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.ServerRepository
import io.reactivex.Completable
import javax.inject.Inject

class SetPrinterIpUseCase @Inject constructor(
    private val serverRepository: ServerRepository
) {

    fun setPrinterIp(
        ip: String,
        kioskId: String
    ): Completable {
        return serverRepository.setPrinterIp(ip, kioskId)
    }

}