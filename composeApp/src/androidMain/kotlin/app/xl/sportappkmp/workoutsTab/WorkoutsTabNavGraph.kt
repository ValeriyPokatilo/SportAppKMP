package app.xl.sportappkmp.workoutsTab

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    data object WorkoutList: Screen("workout_list")
    data object WorkoutCreate: Screen("workout_create/{workout_id}") {
        fun createRoute(id: String): String {
            return "workout_create/${id}"
        }
    }
}

@Composable
fun WorkoutsTabNavGraph() {

    val context = LocalContext.current.applicationContext // TODO: - ???

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutList.route
    ) {
        composable(Screen.WorkoutList.route) {
            WorkoutsScreen(context = context)
        }
    }
}