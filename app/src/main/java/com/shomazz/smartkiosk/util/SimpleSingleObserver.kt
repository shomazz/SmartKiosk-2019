package com.shomazz.smartkiosk.util

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

open class SimpleSingleObserver<T> : SingleObserver<T> {

    override fun onSuccess(obj: T) {
        //no-op
    }

    override fun onSubscribe(d: Disposable) {
        Log.d("Rx", "onSubscribe")
    }

    override fun onError(e: Throwable) {
        Log.d("Rx Error", e.message)
    }


}