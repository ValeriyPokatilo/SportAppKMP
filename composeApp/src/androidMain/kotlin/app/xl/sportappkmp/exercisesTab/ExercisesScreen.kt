package app.xl.sportappkmp.exercisesTab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.exercisesTab.views.ExerciseListRow
import app.xl.sportappkmp.exercisesTab.views.SearchBar
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.viewModels.exercisesTab.ExercisesScreenViewModel

@Composable
fun ExercisesScreen(
    modifier: Modifier = Modifier,
    viewModel: ExercisesScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            query = state.query,
            onQueryChange = { query ->
                viewModel.onQueryChange(query = query)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )

        val localizer = Localizer(LocalContext.current)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(
                items = state.exercises,
                key = { _, exercise -> exercise.id }
            ) { index, exercise ->
                ExerciseListRow(
                    localizer = localizer,
                    exercise = exercise
                )
            }
        }
    }
}