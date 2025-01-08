package org.example.yarldemo.network

import io.ktor.client.engine.HttpClientEngine
import org.example.yarldemo.shared.core.data.AppConfig

class HttpClientEngineFactory {
    fun create(appConfig: AppConfig): HttpClientEngine = getHttpClientEngine(appConfig = appConfig)
}