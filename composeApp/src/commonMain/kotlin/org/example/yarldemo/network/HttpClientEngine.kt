package org.example.yarldemo.network

import io.ktor.client.engine.HttpClientEngine
import org.example.yarldemo.shared.core.data.AppConfig

expect fun getHttpClientEngine(appConfig: AppConfig): HttpClientEngine