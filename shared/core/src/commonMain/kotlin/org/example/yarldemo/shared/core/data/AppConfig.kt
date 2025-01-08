package org.example.yarldemo.shared.core.data

data class AppConfig(
    val host: String,
    val version: Int,
    val versionName: String,
    val debug: Boolean,
)