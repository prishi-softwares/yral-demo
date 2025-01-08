package org.example.yarldemo.common

import org.example.yarldemo.shared.core.data.AppConfig
import org.example.yarldemo.BuildConfig

actual fun getApplicationConfig(): AppConfig {
    return AppConfig(
        host = "https://yral.com",
        version = BuildConfig.VERSION_CODE,
        versionName = BuildConfig.VERSION_NAME,
        debug = BuildConfig.DEBUG,
    )
}