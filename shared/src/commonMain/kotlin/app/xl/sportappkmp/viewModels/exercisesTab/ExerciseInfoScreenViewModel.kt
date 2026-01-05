package app.xl.sportappkmp.viewModels.exercisesTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.models.Exercise
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface InfoScreenState {
    data object Initial: InfoScreenState
    data class Info(val exercise: Exercise): InfoScreenState
    data object GoBack: InfoScreenState
}

class ExerciseInfoScreenViewModel(
    exerciseId: String
): ViewModel() {

    private val repository: ExercisesRepository = ExercisesRepositoryImpl

    private val _state: MutableStateFlow<InfoScreenState> = MutableStateFlow(
        InfoScreenState.Initial
    )
    val state: CStateFlow<InfoScreenState> = _state.asStateFlow().cStateFlow()

    init {
        viewModelScope.launch {
            val exercise = repository.getExercise(exerciseId)
            _state.update {
                InfoScreenState.Info(exercise)
            }
        }
    }

    fun onFinished() {
        _state.update {
            InfoScreenState.GoBack
        }
    }
}