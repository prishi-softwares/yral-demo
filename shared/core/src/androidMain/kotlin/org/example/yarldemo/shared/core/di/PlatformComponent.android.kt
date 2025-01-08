package org.example.yarldemo.shared.core.di

import android.content.Context

actual interface PlatformComponent {
    val applicationContext: Context
    val activityContext: Context
}

class AndroidPlatformComponent(
    private val context: Context
) : PlatformComponent {

    override val applicationContext: Context
        get() = context.applicationContext

    override val activityContext: Context
        get() = context
}
