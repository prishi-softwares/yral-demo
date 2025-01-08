package org.example.yarldemo.shared.extension

import org.example.yarldemo.shared.util.formatInterval
import org.example.yarldemo.shared.util.formatMinSec

internal fun Int.formatMinSec(): String {
    return formatMinSec(this)
}

internal fun Int.formattedInterval(): Int {
    return  formatInterval(this)
}