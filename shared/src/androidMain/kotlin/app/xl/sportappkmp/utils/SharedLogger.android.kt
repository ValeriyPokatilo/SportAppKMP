package app.xl.sportappkmp.utils

import android.util.Log

actual object SharedLogger {
    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}