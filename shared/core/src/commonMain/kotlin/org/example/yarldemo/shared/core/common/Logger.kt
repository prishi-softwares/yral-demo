package org.example.yarldemo.shared.core.common

import co.touchlab.kermit.DefaultFormatter
import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Message
import co.touchlab.kermit.MessageStringFormatter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.Tag
import co.touchlab.kermit.platformLogWriter
import org.example.yarldemo.shared.core.data.AppConfig

class NoOpFormatter: MessageStringFormatter {
    override fun formatMessage(severity: Severity?, tag: Tag?, message: Message): String {
        return ""
    }
}

fun getCustomLogger(tag: String, appConfig: AppConfig): Logger {
    val baseLogger = Logger(
        config = StaticConfig(logWriterList = listOf(getPlatformWriter(appConfig))),
        tag = tag
    )
    return baseLogger.withTag(tag)
}

fun getPlatformWriter(appConfig: AppConfig): LogWriter {
    return platformLogWriter(if (appConfig.debug) DefaultFormatter else NoOpFormatter())
}