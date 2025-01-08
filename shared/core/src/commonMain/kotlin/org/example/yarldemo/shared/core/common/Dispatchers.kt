package org.example.yarldemo.shared.core.common

import kotlinx.coroutines.CoroutineDispatcher
import me.tatarka.inject.annotations.Inject
import org.example.yarldemo.shared.core.di.Singleton
import kotlinx.coroutines.Dispatchers

expect fun provideIODispatcher(): CoroutineDispatcher

@Inject
@Singleton
class Dispatchers {
    val mainDispatcher = Dispatchers.Main
    val ioDispatcher = provideIODispatcher()
    val defaultDispatcher = Dispatchers.Default
    val viewModelDispatcher = Dispatchers.Default
}