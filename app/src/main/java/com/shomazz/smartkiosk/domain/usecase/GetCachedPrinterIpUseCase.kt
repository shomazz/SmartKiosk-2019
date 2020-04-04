package com.shomazz.smartkiosk.domain.usecase

import com.shomazz.smartkiosk.data.repository.PrefsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCachedPrinterIpUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {

    fun getPrinterIp(): Single<String> {
        return prefsRepository.getPrinterIp()
    }

}