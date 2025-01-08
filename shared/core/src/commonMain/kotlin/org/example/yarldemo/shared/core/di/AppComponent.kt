package org.example.yarldemo.shared.core.di

import co.touchlab.kermit.Logger
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Provides
import org.example.yarldemo.shared.core.common.getCustomLogger
import org.example.yarldemo.shared.core.data.AppConfig

@Singleton
interface AppComponent {

    @Provides
    @Singleton
    fun getConfig(): AppConfig

    @Provides
    @Singleton
    fun getLogger(@Assisted tag: String): Logger = getCustomLogger(tag = tag, appConfig = getConfig())
}