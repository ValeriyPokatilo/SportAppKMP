package app.xl.sportappkmp.viewModels.workoutsTab

import app.xl.AppDatabase
import app.xl.ExerciseSet
import app.xl.sportappkmp.data.DatabaseDriverFactory
import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.data.FileManager
import app.xl.sportappkmp.data.SetsRepository
import app.xl.sportappkmp.data.SetsRepositoryImpl
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
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface ActiveWorkoutState {
    data object Initial: ActiveWorkoutState

    data class Ready(
        val workout: Workout,
        val exercises: List<Exercise>
    ): ActiveWorkoutState
}

@OptIn(ExperimentalUuidApi::class)
class ActiveWorkoutScreenViewModel(
    workoutId: String?,
    fileManager: FileManager,
    databaseDriverFactory: DatabaseDriverFactory
) : ViewModel() {

    private val coroutineScope = viewModelScope

    private val database: AppDatabase by lazy {
        AppDatabase(driver = databaseDriverFactory.createDriver())
    }

    private val setsRepository: SetsRepository = SetsRepositoryImpl.getInstance(
        db = database,
        coroutineContext = coroutineScope.coroutineContext
    )

    private val workoutsRepository: WorkoutsRepository = WorkoutsRepositoryImpl.getInstance(
        fileManager
    )
    private val exercisesRepository: ExercisesRepository = ExercisesRepositoryImpl

    private val _state = MutableStateFlow<ActiveWorkoutState>(
        ActiveWorkoutState.Initial
    )
    val state: CStateFlow<ActiveWorkoutState> = _state.asStateFlow().cStateFlow()

    private val _sets = MutableStateFlow<List<ExerciseSet>>(listOf())
    val sets: CStateFlow<List<ExerciseSet>> = _sets.asStateFlow().cStateFlow()

    private val _history = MutableStateFlow<Map<String, List<ExerciseSet>>>(emptyMap())
    val history: CStateFlow<Map<String, List<ExerciseSet>>> = _history.asStateFlow().cStateFlow()

    private var currentExercises: List<Exercise> = emptyList()

    init {
        setsRepository.fetchCurrentSets()
            .onEach { updatedSets ->
                _sets.value = updatedSets
            }
            .launchIn(viewModelScope)

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

                    if (newState is ActiveWorkoutState.Ready) {
                        currentExercises = newState.exercises
                        loadHistory(newState.exercises)
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun onAddSetClick(exerciseId: String, reps: Long, weight: Double, timeStamp: String) {
        viewModelScope.launch {
            setsRepository.addSet(
                ExerciseSet(
                    id = Uuid.random().toString(),
                    exerciseId = exerciseId,
                    reps = reps,
                    weight = weight,
                    timeStamp = timeStamp
                )
            )
        }
    }

    private fun loadHistory(exercises: List<Exercise>) {
        viewModelScope.launch {
            val historyMap = mutableMapOf<String, List<ExerciseSet>>()

            for (exercise in exercises) {
                val previousSets =
                    setsRepository.fetchPreviousSets(exercise.id)

                historyMap[exercise.id] = previousSets
            }

            _history.value = historyMap
        }
    }

    // Debug
    fun shiftAllSetsOneDayBack() {
        viewModelScope.launch {
            setsRepository.shiftAllSetsOneDayBack()

            if (currentExercises.isNotEmpty()) {
                loadHistory(currentExercises)
            }
        }
    }
}