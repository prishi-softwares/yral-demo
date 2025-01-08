package org.example.yarldemo.shared.core.domain.network.base

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class NetworkScope(private val ioDispatcher: CoroutineDispatcher) : CoroutineScope {
    private val job = SupervisorJob()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Logger.e(throwable = throwable) { "coroutine exception" }
    }

    override val coroutineContext: CoroutineContext
        get() = ioDispatcher + job + coroutineExceptionHandler

    fun unsubscribe() {
        job.cancel(CancellationException("Job cancelled"))
    }

    fun unfinishedJobCount(): Int {
        var count = 0
        while (job.children.iterator().hasNext()) count += count
        return count
    }
}