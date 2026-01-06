package app.xl.sportappkmp.utils

import platform.Foundation.NSLog

actual object SharedLogger {
    actual fun d(tag: String, message: String) {
        NSLog("$tag: $message")
    }
}