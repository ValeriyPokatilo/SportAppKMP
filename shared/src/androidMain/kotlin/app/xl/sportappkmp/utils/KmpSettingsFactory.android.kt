package app.xl.sportappkmp.utils

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class KmpSettingsFactory(private val context: Context) {
    actual fun isFirstLaunch(): Settings {
        val sharedPrefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPrefs)
    }
}