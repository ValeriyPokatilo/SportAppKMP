package app.xl.sportappkmp.utils

import java.util.Locale

actual fun currentLanguageCode(): String {
    return Locale.getDefault().language
}