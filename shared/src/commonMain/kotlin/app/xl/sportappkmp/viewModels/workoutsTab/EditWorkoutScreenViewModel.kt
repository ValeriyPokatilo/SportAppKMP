package app.xl.sportappkmp.viewModels.workoutsTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.data.WorkoutsRepository
import app.xl.sportappkmp.data.WorkoutsRepositoryImpl
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.Workout
import app.xl.sportappkmp.models.WorkoutUI
import app.xl.sportappkmp.usecase.bindWorkoutsWithExercises
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WorkoutInfoState(
    val id: String = "",
    val title: String = "",
    val exercises: List<Exercise> = listOf(),
    val workoutUI: WorkoutUI? = null,
    val titleError: Boolean = false, // TODO: - Show error
    val exercisesError: Boolean = false, // TODO: - Show error
)

class EditWorkoutScreenViewModel(
    workoutId: String?,
    fileManager: FileManager,
): ViewModel() {

    private val _state: MutableStateFlow<WorkoutInfoState> = MutableStateFlow(
        WorkoutInfoState()
    )
    val state: CStateFlow<WorkoutInfoState> = _state.asStateFlow().cStateFlow()

    private val workoutsRepository: WorkoutsRepository = WorkoutsRepositoryImpl.getInstance(fileManager)
    private val exercisesRepository: ExercisesRepository = ExercisesRepositoryImpl

    init {
        workoutId?.let { id ->
            viewModelScope.launch {
                val workout = workoutsRepository.getWorkout(id)
                val allExercises = exercisesRepository
                    .getAllExercises()
                    .first()

                val workoutUI = bindWorkoutsWithExercises(
                    workouts = listOf(workout),
                    exercises = allExercises
                ).firstOrNull()

                _state.value = WorkoutInfoState(
                    id = workout.id,
                    title = workoutUI?.title.orEmpty(),
                    exercises = workoutUI?.exercises.orEmpty(),
                    workoutUI = workoutUI,
                    titleError = false,
                    exercisesError = false
                )
            }
        }
    }

    fun onTitleChanged(title: String) {
        _state.update {
            it.copy(
                title = title,
                workoutUI = it.workoutUI?.copy(title = title)
            )
        }
    }

    fun onDeleteExercise(exerciseId: String) {
        _state.update { current ->
            val updatedExercises =
                current.exercises.filterNot { it.id == exerciseId }

            current.copy(
                exercises = updatedExercises,
                workoutUI = current.workoutUI?.copy(
                    exercises = updatedExercises
                ),
                exercisesError = false
            )
        }
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            workoutsRepository.saveWorkout(workout = Workout(
                id = state.value.id,
                title = state.value.title,
                exerciseIds = state.value.exercises.map { it.id }
            ))
        }
    }
}