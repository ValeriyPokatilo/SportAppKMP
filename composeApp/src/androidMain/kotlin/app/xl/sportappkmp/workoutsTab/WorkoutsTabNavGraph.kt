package app.xl.sportappkmp.workoutsTab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    data object WorkoutList: Screen("workout_list")
    data object EditWorkout: Screen("edit_workout/{workout_id}") {
        fun createRoute(id: String?): String {
            return "edit_workout/${id}"
        }
    }
    data object ActiveWorkout: Screen("active_workout/{workout_id}") {
        fun createRoute(id: String): String {
            return "active_workout/${id}"
        }
    }
}

@Composable
fun WorkoutsTabNavGraph(
    modifier: Modifier = Modifier,
    onFabActionChanged: ((() -> Unit)?) -> Unit
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutList.route
    ) {
        composable(Screen.WorkoutList.route) {
            LaunchedEffect(Unit) {
                onFabActionChanged {
                    navController.navigate(
                        Screen.EditWorkout.createRoute(null)
                    )
                }
            }

            WorkoutsScreen(
                modifier = modifier,
                onAddWorkoutClick = { id ->
                    navController.navigate(Screen.EditWorkout.createRoute(id))
                },
                onStartWorkoutClick = { id ->
                    navController.navigate(Screen.ActiveWorkout.createRoute(id))
                }
            )
        }

        composable(Screen.EditWorkout.route) { navBackStackEntry ->
            LaunchedEffect(Unit) {
                onFabActionChanged(null)
            }

            val id = navBackStackEntry.arguments?.getString("workout_id")
            EditWorkoutScreen(
                workoutId = id,
                onFinished = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ActiveWorkout.route) { navBackStackEntry ->
            LaunchedEffect(Unit) {
                onFabActionChanged(null)
            }

            val id = navBackStackEntry.arguments?.getString("workout_id") ?: ""

            ActiveWorkoutScreen(
                workoutId = id,
                onEdit = {
                    navController.navigate(Screen.EditWorkout.createRoute(id))
                },
                onFinished = {
                    navController.popBackStack()
                }
            )
        }
    }
}