package org.example.yarldemo.shared.core.di

import org.example.yarldemo.shared.core.domain.network.HttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import me.tatarka.inject.annotations.Provides
import org.example.yarldemo.shared.core.data.AppConfig

@Singleton
interface NetworkComponent: AppComponent {

    @Provides
    @Singleton
    fun getHttpClientEngine(): HttpClientEngine

    @Provides
    @Singleton
    fun getHttpClient(
        httpClientEngine: HttpClientEngine,
        appConfig: AppConfig,
    ): HttpClient = HttpClientFactory.create(
        httpClientEngine = httpClientEngine,
        appConfig = appConfig,
    )
}