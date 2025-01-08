package org.example.yarldemo.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.engine.okhttp.OkHttpEngine
import okhttp3.CertificatePinner
import org.example.yarldemo.shared.core.data.AppConfig
import org.example.yarldemo.shared.core.domain.network.base.REQUEST_TIME_OUT
import java.util.concurrent.TimeUnit

actual fun getHttpClientEngine(appConfig: AppConfig): HttpClientEngine {
    val certificatePinner = CertificatePinner.Builder()
        .build()
    val okhttpConfig = OkHttpConfig()
    okhttpConfig.config {
        certificatePinner(certificatePinner = certificatePinner)
        connectTimeout(timeout = REQUEST_TIME_OUT.toLong(), unit = TimeUnit.SECONDS)
        readTimeout(timeout = REQUEST_TIME_OUT.toLong(), unit = TimeUnit.SECONDS)
        writeTimeout(timeout = REQUEST_TIME_OUT.toLong(), unit = TimeUnit.SECONDS)
        if (appConfig.debug) {
            addInterceptor(interceptor = CurlLoggingInterceptor())
        }
    }
    return OkHttpEngine(config = okhttpConfig)
}