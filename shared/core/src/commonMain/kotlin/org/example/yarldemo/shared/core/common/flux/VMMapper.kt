package org.example.yarldemo.shared.core.common.flux

interface VMMapper<State, VM> {
    fun map(state: State): VM
}