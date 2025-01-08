package org.example.yarldemo.shared.core.common.flux

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

abstract class StateReducer<State>(
    private val stateDispatcher: CoroutineDispatcher
) {
    abstract val initState: State

    private val mutableStateStream by lazy { MutableStateFlow(initState) }

    open val currState: State
        get() = mutableStateStream.value

    val stateStream: Flow<State>
        get() = mutableStateStream

    protected suspend fun updateState(func: (State) -> State): State =
        withContext(stateDispatcher) {
            func(currState).also {
                Logger.d("updateState called $it")
                mutableStateStream.value = it
            }
        }
}