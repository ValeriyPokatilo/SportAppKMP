package app.xl.sportappkmp.workoutsTab

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.viewModels.workoutsTab.WorkoutsScreenViewModel
import app.xl.sportappkmp.workoutsTab.views.WorkoutItemRow

@Composable
fun WorkoutsScreen(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: WorkoutsScreenViewModel = viewModel {
        WorkoutsScreenViewModel(fileManager = FileManager(context))
    }
) {

    val workouts by viewModel.workouts.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = workouts,
            key = { _, workoutUi -> workoutUi.title }
        ) { index, workoutUi ->
            WorkoutItemRow(
                modifier =  Modifier.padding(horizontal = 24.dp),
                workoutUi = workoutUi
            )
        }
    }
}