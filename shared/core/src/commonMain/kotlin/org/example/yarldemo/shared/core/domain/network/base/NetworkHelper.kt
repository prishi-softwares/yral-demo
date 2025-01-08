package org.example.yarldemo.shared.core.domain.network.base

import co.touchlab.kermit.Logger
import org.example.yarldemo.shared.core.domain.network.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.KSerializer
import org.example.yarldemo.shared.core.data.ApiEndPoints
import org.example.yarldemo.shared.core.utils.json
import org.example.yarldemo.shared.core.utils.toJsonElement

suspend fun call(httpClient: HttpClient, url: String, callback: SimpleCallback) {
    try {
        val response: HttpResponse = httpClient.get(url)
        if (response.status.value == 200) {
            callback.onResponse(response.body())
        }
    } catch (e: Exception) {
        callback.onFailure(e)
    }
}

suspend fun <T> get(httpClient: HttpClient, url: String, apiEndPoints: ApiEndPoints, callBack: NetworkCallbacks<T>, deserializer: KSerializer<T>) {
    get(httpClient, url, apiEndPoints, callBack, deserializer, extras = null)
}

suspend fun <T> get(httpClient: HttpClient, url: String, apiEndPoints: ApiEndPoints, callBack: NetworkCallbacks<T>, deserializer: KSerializer<T>, extras: HashMap<String, Any?>?) {
    try {
        val response: HttpResponse = httpClient.get(url + apiEndPoints.endPoint) {
            contentType(ContentType.Application.Json)
        }
        if (response.status.value == 200) {
            try {
                val apiResponseString = response.bodyAsText()
                val apiResponse = json.decodeFromString(BaseResponse.serializer(deserializer), apiResponseString)
                if (apiResponse.success) {
                    callBack.onSuccess(apiEndPoints.tag, apiResponse, extras)
                } else {
                    callBack.onFailure(apiEndPoints.tag, apiResponse, extras)
                }
            } catch (e: Exception) {
                callBack.onError(apiEndPoints.tag, e, extras)
                e.message?.let { Logger.e("I am here in ${apiEndPoints.endPoint} and cause is: " + it) }
            }
        }
    } catch (e: RedirectResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ServerResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ClientRequestException) {
        if (e.response.status.value == 401) {
            callBack.onSessionExpired()
        } else {
            callBack.onError(apiEndPoints.tag, e, extras)
        }
    }
}

suspend fun <T> post(httpClient: HttpClient, url: String, apiEndPoints: ApiEndPoints, requestObject: Any, callBack: NetworkCallbacks<T>, deserializer: KSerializer<T>, extras: HashMap<String, Any?>?) {
    try {
        val response: HttpResponse = httpClient.post(url + apiEndPoints.endPoint) {
            contentType(ContentType.Application.Json)
            setBody(requestObject)
        }
        if (response.status.value == 200) {
            try {
                val apiResponseString = response.body<String>()
                val apiResponse = json.decodeFromString(BaseResponse.serializer(deserializer), apiResponseString)
                if (apiResponse.success) {
                    callBack.onSuccess(apiEndPoints.tag, apiResponse, extras)
                } else {
                    callBack.onFailure(apiEndPoints.tag, apiResponse, extras)
                }
            } catch (e: Exception) {
                callBack.onError(apiEndPoints.tag, e, extras)
                e.message?.let { Logger.e("I am here in ${apiEndPoints.endPoint} and cause is: " + it) }
            }
        }
    } catch (e: RedirectResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ServerResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ClientRequestException) {
        if (e.response.status.value == 401) {
            callBack.onSessionExpired()
        } else {
            callBack.onError(apiEndPoints.tag, e, extras)
        }
    }
}

suspend fun <T> post(httpClient: HttpClient, url: String, apiEndPoints: ApiEndPoints, requestBody: HashMap<String, Any?>, callBack: NetworkCallbacks<T>, deserializer: KSerializer<T>, extras: HashMap<String, Any?>?) {
    try {
        val response: HttpResponse = httpClient.post(url + apiEndPoints.endPoint) {
            contentType(ContentType.Application.Json)
            setBody(requestBody.toJsonElement())
        }
        if (response.status.value == 200) {
            try {
                val apiResponseString = response.bodyAsText()
                val apiResponse = json.decodeFromString(BaseResponse.serializer(deserializer), apiResponseString)
                if (apiResponse.success) {
                    callBack.onSuccess(apiEndPoints.tag, apiResponse, extras)
                } else {
                    callBack.onFailure(apiEndPoints.tag, apiResponse, extras)
                }
            } catch (e: Exception) {
                callBack.onError(apiEndPoints.tag, e, extras)
                e.message?.let { Logger.e("I am here in ${apiEndPoints.endPoint} and cause is: " + it) }
            }
        }
    } catch (e: RedirectResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ServerResponseException) {
        callBack.onError(apiEndPoints.tag, e, extras)
    } catch (e: ClientRequestException) {
        if (e.response.status.value == 401) {
            callBack.onSessionExpired()
        } else {
            callBack.onError(apiEndPoints.tag, e, extras)
        }
    }
}