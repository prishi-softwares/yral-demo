package org.example.yarldemo.shared.core.domain.network.base

import co.touchlab.kermit.Logger
import org.example.yarldemo.shared.core.domain.network.BaseResponse

interface NetworkCallbacks<T> {
    fun onSessionExpired()

    fun onSuccess(callTag: String, response: BaseResponse<T>?, extras: HashMap<String, Any?>?)

    fun onFailure(callTag: String, response: BaseResponse<T>?, extras: HashMap<String, Any?>?)

    fun onError(callTag: String, e: Throwable?, extras: HashMap<String, Any?>?)
}

abstract class EmptyCallBacks<T> : NetworkCallbacks<T> {
    override fun onSessionExpired() {}

    override fun onFailure(callTag: String, response: BaseResponse<T>?, extras: HashMap<String, Any?>?) {}

    override fun onError(callTag: String, e: Throwable?, extras: HashMap<String, Any?>?) {
        Logger.e { e.toString() }
    }
}

abstract class SimpleCallback {
    abstract fun onResponse(body: HashMap<String?, Any?>?)

    abstract fun onFailure(e: Exception)
}