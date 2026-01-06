package app.xl.sportappkmp.viewModels.workoutsTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.data.WorkoutsRepository
import app.xl.sportappkmp.data.WorkoutsRepositoryImpl
import app.xl.sportappkmp.models.WorkoutUI
import app.xl.sportappkmp.usecase.bindWorkoutsWithExercises
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class WorkoutsScreenViewModel: ViewModel() {
    private val workoutsRepository: WorkoutsRepository = WorkoutsRepositoryImpl
    private val exercisesRepository: ExercisesRepository = ExercisesRepositoryImpl

    val workouts: CStateFlow<List<WorkoutUI>> =
        bindWorkoutsWithExercises(
            workoutsRepository.getAllWorkouts(),
            exercisesRepository.getAllExercises()
        ).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        ).cStateFlow()


    // TODO: - Use EventsDispatcher
}