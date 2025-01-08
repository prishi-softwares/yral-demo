package org.example.yarldemo.shared.core.domain.network

import org.example.yarldemo.shared.core.domain.network.base.HTTP_LOGGING_TAG
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.example.yarldemo.shared.core.data.AppConfig
import org.example.yarldemo.shared.core.utils.json

object HttpClientFactory {
    fun create(
        httpClientEngine: HttpClientEngine,
        appConfig: AppConfig,
    ): HttpClient = HttpClient(httpClientEngine) {
        install(Logging) {
            level = if (appConfig.debug) LogLevel.BODY else LogLevel.NONE
            logger = object: Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.withTag(HTTP_LOGGING_TAG).i { message }
                }
            }
        }
        install(ContentNegotiation) {
            json(json = json, ContentType.Application.Json)
        }
        defaultRequest {
            headers {
            }
        }
        addDefaultResponseValidation()
    }
}