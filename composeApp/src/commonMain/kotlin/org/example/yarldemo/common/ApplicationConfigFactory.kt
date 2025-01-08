package org.example.yarldemo.common

import me.tatarka.inject.annotations.Inject
import org.example.yarldemo.shared.core.data.AppConfig

@Inject
class ApplicationConfigFactory {
    fun create(): AppConfig = getApplicationConfig()
}