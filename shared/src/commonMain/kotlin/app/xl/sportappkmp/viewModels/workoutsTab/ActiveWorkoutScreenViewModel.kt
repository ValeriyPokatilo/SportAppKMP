package app.xl.sportappkmp.viewModels.workoutsTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.data.WorkoutsRepository
import app.xl.sportappkmp.data.WorkoutsRepositoryImpl
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.Workout
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

sealed interface ActiveWorkoutState {
    data object Initial: ActiveWorkoutState

    data class Ready(
        val workout: Workout,
        val exercises: List<Exercise>
    ): ActiveWorkoutState
}

class ActiveWorkoutScreenViewModel(
    workoutId: String?,
    fileManager: FileManager
) : ViewModel() {

    private val workoutsRepository: WorkoutsRepository = WorkoutsRepositoryImpl.getInstance(
        fileManager
    )

    private val exercisesRepository: ExercisesRepository = ExercisesRepositoryImpl

    private val _state = MutableStateFlow<ActiveWorkoutState>(
        ActiveWorkoutState.Initial
    )
    val state: CStateFlow<ActiveWorkoutState> = _state.asStateFlow().cStateFlow()

    init {
        workoutId?.let { id ->
            combine(
                workoutsRepository.getAllWorkouts(),
                exercisesRepository.getAllExercises()
            ) { workouts, exercises ->
                val workout = workouts.firstOrNull { it.id == id }

                workout?.let {
                    ActiveWorkoutState.Ready(
                        workout = it,
                        exercises = it.exerciseIds.mapNotNull { exId ->
                            exercises.firstOrNull { e -> e.id == exId }
                        }
                    )
                } ?: ActiveWorkoutState.Initial // TODO: - Show error
            }
                .onEach { newState ->
                    _state.value = newState
                }
                .launchIn(viewModelScope)
        }
    }
}