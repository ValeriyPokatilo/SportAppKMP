package app.xl.sportappkmp.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class KmpSettingsFactory {
    actual fun isFirstLaunch(): Settings {
        val userDefaults = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(userDefaults)
    }
}