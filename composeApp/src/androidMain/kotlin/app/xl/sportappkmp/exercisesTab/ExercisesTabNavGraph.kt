package app.xl.sportappkmp.exercisesTab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    data object ExerciseList: Screen("exercise_list")
    data object ExerciseInfo: Screen("exercise_info/{exercise_id}") {
        fun createRoute(id: String): String {
            return "exercise_info/${id}"
        }
    }
}

@Composable
fun ExercisesTabNavGraph(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ExerciseList.route,
    ) {

        composable(Screen.ExerciseList.route) {
            ExercisesScreen(
                modifier = modifier,
                onExerciseClick = { id ->
                    navController.navigate(Screen.ExerciseInfo.createRoute(id))
                }
            )
        }

        composable(Screen.ExerciseInfo.route) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("exercise_id") ?: ""

            ExerciseInfoScreen(
                exerciseId = id,
                onFinished = {
                    navController.popBackStack()
                }
            )
        }
    }
}
