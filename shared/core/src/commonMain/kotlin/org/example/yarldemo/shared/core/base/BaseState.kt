package org.example.yarldemo.shared.core.base

import org.example.yarldemo.shared.core.common.flux.StateReducer
import org.example.yarldemo.shared.core.common.flux.VMMapper
import kotlinx.coroutines.CoroutineDispatcher
import me.tatarka.inject.annotations.Inject
import org.example.yarldemo.shared.core.common.Dispatchers
import org.example.yarldemo.shared.core.di.Named
import org.example.yarldemo.shared.core.di.Singleton

data class BaseState(
    var isLoading: Boolean = false,
    var isAuthExpired: Boolean = false
)

@Inject
@Singleton
class BaseStateReducer(
    dispatchers: Dispatchers
): StateReducer<BaseState>(dispatchers.ioDispatcher) {

    override val initState: BaseState
        get() = BaseState()

    suspend fun setLoading(isLoading: Boolean) {
        updateState {
            it.copy(isLoading = isLoading)
        }
    }

    suspend fun setAuthExpired(isAuthExpired: Boolean) {
        updateState {
            it.copy(isAuthExpired = isAuthExpired)
        }
    }
}

data class BaseVM(
    var isLoading: Boolean = false,
    var isAuthExpired: Boolean = false
)

@Inject
@Singleton
class BaseVMMapper: VMMapper<BaseState, BaseVM> {
    override fun map(state: BaseState): BaseVM {
        return BaseVM(
            isLoading = state.isLoading,
            isAuthExpired = state.isAuthExpired
        )
    }
}