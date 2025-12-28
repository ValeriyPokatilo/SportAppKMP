package app.xl.sportappkmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import app.xl.sportappkmp.exercisesTab.ExercisesScreen
import app.xl.sportappkmp.infoTab.InfoScreen
import app.xl.sportappkmp.workoutsTab.WorkoutsScreen

@Composable
fun BottomBarScreen() {
    val items = rememberSaveable {
        listOf(
            BottomBarItem(
                title = "Тренировки", // TODO: - Move to resources
                destination = BottomBarDestination.WORKOUTS,
                selectedIcon = Icons.Filled.Home,  // TODO: - Move to resources
                unselectedIcon = Icons.Outlined.Home  // TODO: - Move to resources
            ),
            BottomBarItem(
                title = "Упражнения", // TODO: - Move to resources
                destination = BottomBarDestination.EXERCISES,
                selectedIcon = Icons.Filled.Home, // TODO: - Move to resources
                unselectedIcon = Icons.Outlined.Home // TODO: - Move to resources
            ),
            BottomBarItem(
                title = "Инфо", // TODO: - Move to resources
                destination = BottomBarDestination.INFO,
                selectedIcon = Icons.Filled.Home, // TODO: - Move to resources
                unselectedIcon = Icons.Outlined.Home // TODO: - Move to resources
            )
        )
    }

    var selectedDestination by rememberSaveable {
        mutableStateOf(BottomBarDestination.EXERCISES) // TODO: - set WORKOUTS
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->

                    val selected = item.destination == selectedDestination

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            selectedDestination = item.destination
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        val paddingModifier = Modifier.padding(innerPadding)
        when (selectedDestination) {
            BottomBarDestination.WORKOUTS -> {
                WorkoutsScreen(modifier = paddingModifier)
            }
            BottomBarDestination.EXERCISES-> {
                ExercisesScreen(modifier = paddingModifier)
            }
            BottomBarDestination.INFO -> {
                InfoScreen(modifier = paddingModifier)
            }
        }
    }
}

data class BottomBarItem(
    val title: String,
    val destination: BottomBarDestination,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

enum class BottomBarDestination {
    WORKOUTS,
    EXERCISES,
    INFO
}