package app.xl.sportappkmp.exercisesTab

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.viewModels.exercisesTab.ExercisesScreenViewModel

@Composable
fun ExercisesScreen(
    modifier: Modifier = Modifier,
    viewModel: ExercisesScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            query = state.query,
            onQueryChange = { query ->
                viewModel.onQueryChange(query = query)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(
                items = state.exercises,
                key = { _, exercise -> exercise.id }
            ) { index, exercise ->
                ExerciseListRow(exercise = exercise)
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width =  1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(10.dp)
            ),
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Search ...",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search notes",
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun ExerciseListRow(
    modifier: Modifier = Modifier,
    exercise: Exercise
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = exercise.titleRu, // TODO: - use localizedTitle
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = exercise.muscleGroups.joinToString(", "), // TODO: - use localizedTitle
            fontSize = 24.sp
        )

        Text(
            text = exercise.equipment.joinToString(", "), // TODO: - use localizedTitle
            fontSize = 24.sp
        )

        Text(
            text = exercise.unitType.toString(), // TODO: - use localizedTitle
            fontSize = 24.sp
        )
    }
}