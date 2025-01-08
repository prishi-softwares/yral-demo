package org.example.yarldemo.shared.core.common.exceptionHandler

import kotlinx.coroutines.CoroutineExceptionHandler
import me.tatarka.inject.annotations.Inject
import org.example.yarldemo.shared.core.di.Singleton

data class AppCoroutineExceptionHandler(
    val foregroundExceptionHandler: CoroutineExceptionHandler,
    val backgroundExceptionHandler: CoroutineExceptionHandler
)

@Inject
@Singleton
class AppCoroutineExceptionHandlerProvider {
    val interactorExceptionHandler = AppCoroutineExceptionHandler(
        foregroundExceptionHandler = CoroutineExceptionHandler { _, throwable ->

        },
        backgroundExceptionHandler = CoroutineExceptionHandler { _, throwable ->

        }
    )
}