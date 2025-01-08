package org.example.yarldemo.shared.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform