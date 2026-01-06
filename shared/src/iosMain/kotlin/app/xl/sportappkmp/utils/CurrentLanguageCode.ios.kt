package app.xl.sportappkmp.utils

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun currentLanguageCode(): String {
    return NSLocale.currentLocale.languageCode ?: "en"
}