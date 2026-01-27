@file:OptIn(ExperimentalMaterial3Api::class)

package app.xl.sportappkmp.workoutsTab

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.commonViews.Loader
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.viewModels.workoutsTab.ActiveWorkoutScreenViewModel
import app.xl.sportappkmp.viewModels.workoutsTab.ActiveWorkoutState
import app.xl.sportappkmp.workoutsTab.views.ActiveWorkoutRow
import app.xl.sportappkmp.workoutsTab.views.AddSetPicker

@Composable
fun ActiveWorkoutScreen(
    modifier: Modifier = Modifier,
    workoutId: String,
    context: Context = LocalContext.current.applicationContext,
    viewModel: ActiveWorkoutScreenViewModel = viewModel {
        ActiveWorkoutScreenViewModel(workoutId, FileManager(context))
    },
    onEdit: () -> Unit,
    onFinished: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedExerciseId by remember { mutableStateOf<String?>(null) }

    val title = when (val currentState = state) {
        is ActiveWorkoutState.Ready -> currentState.workout.title
        ActiveWorkoutState.Initial -> ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
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
                                onEdit()
                            },
                        imageVector = Icons.Outlined.EditNote,
                        contentDescription = "Edit note"
                    )
                }
            )
        }
    ) { innerPadding ->
        when (state) {
            ActiveWorkoutState.Initial -> {
                Loader(context)
            }
            is ActiveWorkoutState.Ready -> {
                val exercises = (state as ActiveWorkoutState.Ready).exercises

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                        .background(Color(MR.colors.baseGray.getColor(context)))
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 24.dp),
                    ) {
                        itemsIndexed(
                            items = exercises,
                            key = { _, exercise -> exercise.id }
                        ) { _, exercise ->
                            ActiveWorkoutRow(
                                exerciseTitle = exercise.localizedTitle,
                                exerciseIconName = exercise.iconName,
                                onAddSetClick = {
                                    selectedExerciseId = exercise.localizedTitle // TODO: - Use id
                                }
                            )
                        }
                    }
                }
            }
            // TODO: - incorrect bottom padding
        }

        selectedExerciseId?.let { exerciseId ->
            Dialog(
                onDismissRequest = { selectedExerciseId = null },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                AddSetPicker(
                    exerciseId = exerciseId,
                    onCancelClick = {
                        selectedExerciseId = null
                    },
                    onSaveClick = {}
                )
            }
        }
    }
}