package org.example.yarldemo.shared.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO