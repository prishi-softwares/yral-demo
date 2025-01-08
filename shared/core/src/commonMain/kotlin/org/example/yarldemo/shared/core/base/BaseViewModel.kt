package org.example.yarldemo.shared.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import org.example.yarldemo.shared.core.common.Dispatchers
import org.example.yarldemo.shared.core.common.exceptionHandler.AppCoroutineExceptionHandlerProvider
import org.example.yarldemo.shared.core.common.flux.StateReducer
import org.example.yarldemo.shared.core.common.flux.VMMapper
import org.example.yarldemo.shared.core.di.Singleton
import org.example.yarldemo.shared.core.domain.network.base.NetworkScope

abstract class BaseViewModel<State, VM>(
    private val coroutineHelper: CoroutineHelper,
    stateReducer: StateReducer<State>,
    private val vmMapper: VMMapper<State, VM>,
    private val baseStateReducer: BaseStateReducer,
    private val baseVMMapper: BaseVMMapper,
): ViewModel() {

    private val mutableVMStream = MutableStateFlow(vmMapper.map(stateReducer.currState))
    val vmStream: StateFlow<VM> = mutableVMStream

    private val mutableBaseVMStream = MutableStateFlow(baseVMMapper.map(baseStateReducer.currState))
    val baseVMStream: StateFlow<BaseVM> = mutableBaseVMStream

    init {
        baseStateReducer.stateStream
            .mapNotNull { baseVMMapper.map(it) }
            .onEach {
                mutableBaseVMStream.emit(it)
            }
            .launchIn(viewModelScope)
        stateReducer.stateStream
            .mapNotNull { vmMapper.map(it) }
            .onEach {
                mutableVMStream.emit(it)
            }
            .launchIn(viewModelScope)
    }

    protected fun launchSafeForeground(job: suspend () -> Unit): Job =
        runJob(
            handler = coroutineHelper.exceptionHandler.interactorExceptionHandler.foregroundExceptionHandler,
            job
        )

    protected fun launchSafeBackground(job: suspend () -> Unit): Job =
        runJob(
            handler = coroutineHelper.exceptionHandler.interactorExceptionHandler.backgroundExceptionHandler,
            job
        )

    private fun runJob(
        handler: CoroutineExceptionHandler,
        job: suspend () -> Unit
    ): Job =
        viewModelScope.launch(
            context = coroutineHelper.dispatchers.viewModelDispatcher + handler) {
            job.invoke()
        }

    val networkScope = NetworkScope(coroutineHelper.dispatchers.ioDispatcher)

    override fun onCleared() {
        super.onCleared()
        networkScope.unsubscribe()
    }

    fun setLoading(isLoading: Boolean) {
        launchSafeBackground {
            baseStateReducer.setLoading(isLoading)
        }
    }

    fun onSessionExpired() {
        launchSafeBackground {
            baseStateReducer.setAuthExpired(isAuthExpired = true)
        }
    }
}

@Inject
@Singleton
class CoroutineHelper(
    val dispatchers: Dispatchers,
    val exceptionHandler: AppCoroutineExceptionHandlerProvider
)

