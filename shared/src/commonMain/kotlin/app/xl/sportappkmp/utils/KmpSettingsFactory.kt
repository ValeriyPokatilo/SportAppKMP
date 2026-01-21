package app.xl.sportappkmp.utils

import com.russhwolf.settings.Settings

class FirstLaunchManager(private val settings: Settings) {

    companion object {
        private const val KEY_FIRST_LAUNCH = "is_first_launch"
    }

    fun getAndCheckFirstLaunch(): Boolean {
        val isFirst = settings.getBoolean(KEY_FIRST_LAUNCH, true)
        if (isFirst) {
            settings.putBoolean(KEY_FIRST_LAUNCH, false)
        }
        return isFirst
    }
}

expect class KmpSettingsFactory {
    fun isFirstLaunch(): Settings
}