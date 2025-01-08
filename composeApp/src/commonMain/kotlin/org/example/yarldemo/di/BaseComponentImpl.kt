package org.example.yarldemo.di

import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavHostController
import io.ktor.client.engine.HttpClientEngine
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides
import org.example.yarldemo.common.ApplicationConfigFactory
import org.example.yarldemo.network.HttpClientEngineFactory
import org.example.yarldemo.shared.core.data.AppConfig
import org.example.yarldemo.shared.core.di.BaseComponent
import org.example.yarldemo.shared.core.di.PlatformComponent
import org.example.yarldemo.shared.core.di.Singleton

@Component
abstract class BaseComponentImpl(
    @get:Provides val navController: NavHostController,
    @get:Provides val toastHostState: SnackbarHostState,
    @get:Provides val platformComponent: PlatformComponent,
): BaseComponent {

    @Singleton
    private fun getApplicationConfigFactory(): ApplicationConfigFactory = ApplicationConfigFactory()

    override fun getConfig(): AppConfig = getApplicationConfigFactory().create()

    @Singleton
    private fun getHttpClientEngineFactory(): HttpClientEngineFactory = HttpClientEngineFactory()
    
    override fun getHttpClientEngine(): HttpClientEngine = getHttpClientEngineFactory().create(getConfig())
}

@KmpComponentCreate
expect fun createBaseComponent(
    navController: NavHostController,
    toastHostState: SnackbarHostState,
    platformComponent: PlatformComponent,
): BaseComponentImpl