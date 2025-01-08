package org.example.yarldemo.shared.core.domain.network.base

import org.example.yarldemo.shared.core.domain.network.BaseResponse

class CallBackMapper<T>(val callBack: NetworkCallbacks<Any?>) : NetworkCallbacks<T> {
    override fun onSessionExpired() {
        callBack.onSessionExpired()
    }

    override fun onSuccess(callTag: String, response: BaseResponse<T>?, extras: HashMap<String, Any?>?) {
        response?.let {
            callBack.onSuccess(callTag, convertData(response), extras)
        }
    }

    override fun onFailure(callTag: String, response: BaseResponse<T>?, extras: HashMap<String, Any?>?) {
        response?.let {
            callBack.onFailure(callTag, convertData(response), extras)
        }
    }

    override fun onError(callTag: String, e: Throwable?, extras: HashMap<String, Any?>?) {
        callBack.onError(callTag, e, extras)
    }
}

fun <T> convertData(response: BaseResponse<T>): BaseResponse<Any?> {
    return BaseResponse(success = response.success,
        msg = response.msg, message = response.message, statusCode = response.statusCode,
        data = response.data as Any?)
}