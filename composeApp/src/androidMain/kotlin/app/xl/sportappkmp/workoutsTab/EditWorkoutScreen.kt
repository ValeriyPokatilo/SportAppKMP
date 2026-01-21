package app.xl.sportappkmp.workoutsTab

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.exercisesTab.views.ExerciseListRow
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.utils.localizer
import app.xl.sportappkmp.viewModels.workoutsTab.EditWorkoutScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditWorkoutScreen(
    modifier: Modifier = Modifier,
    workoutId: String?,
    context: Context = LocalContext.current.applicationContext,
    viewModel: EditWorkoutScreenViewModel = viewModel {
        EditWorkoutScreenViewModel(workoutId, FileManager(context))
    },
    onFinished: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val localizer = Localizer(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(MR.colors.baseGray.getColor(context)),
                    navigationIconContentColor = Color.DarkGray,
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .clickable {
                                onFinished()
                            },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                viewModel.onSaveClicked()
                                onFinished()
                            },
                        imageVector = Icons.Outlined.Save,
                        contentDescription = "Save note"
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(MR.colors.baseGray.getColor(context)))
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .background(Color(MR.colors.baseGray.getColor(context))),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White
                ),
                value = state.title,
                onValueChange = {
                    viewModel.onTitleChanged(it)
                },
                placeholder = {
                    Text(
                        text = localizer(MR.strings.workoutNameStr),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(10.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                itemsIndexed(
                    items = state.exercises,
                    key = { _, exercise -> exercise.id }
                ) { index, exercise ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ExerciseListRow(
                            modifier = Modifier.fillMaxWidth(),
                            localizer = localizer,
                            exercise = exercise,
                            onClick = {}
                        )

                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(48.dp)
                                .padding(8.dp)
                                .clickable {
                                    viewModel.onDeleteExercise(exercise.id)
                                },
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red,
                        )
                    }
                }
            }
            // TODO: - incorrect bottom padding
        }
    }
}