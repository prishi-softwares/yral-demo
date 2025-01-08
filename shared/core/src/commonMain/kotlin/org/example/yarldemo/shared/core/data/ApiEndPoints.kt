package org.example.yarldemo.shared.core.data

class ApiEndPoints(val tag: String, val endPoint: String)

enum class AppApiEndPoints(val endPoint: ApiEndPoints) {

    APP_CONFIG(ApiEndPoints("AppConfig", "yral/appConfig"));

    val tag = endPoint.tag

    override fun toString(): String {
        return endPoint.tag
    }
}
