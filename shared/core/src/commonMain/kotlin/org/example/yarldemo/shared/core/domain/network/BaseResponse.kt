package org.example.yarldemo.shared.core.domain.network

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(val success: Boolean = false,
                           val msg: String = "",
                           val message: String? = "",
                           val statusCode: String? = "",
                           var data: T? = null)