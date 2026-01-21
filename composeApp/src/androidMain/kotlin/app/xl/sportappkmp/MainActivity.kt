package app.xl.sportappkmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.xl.sportappkmp.data.DemoWorkouts
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.utils.FirstLaunchManager
import app.xl.sportappkmp.utils.KmpSettingsFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        checkFirstLaunch()

        setContent {
            MainScreen()
        }
    }

    private fun checkFirstLaunch() {
        val factory = KmpSettingsFactory(applicationContext)
        val manager = FirstLaunchManager(factory.isFirstLaunch())

        val isFirst = manager.checkFirstLaunch()
        if (isFirst) {
            DemoWorkouts.createDemoWorkouts(FileManager(context = applicationContext))
        }
    }
}