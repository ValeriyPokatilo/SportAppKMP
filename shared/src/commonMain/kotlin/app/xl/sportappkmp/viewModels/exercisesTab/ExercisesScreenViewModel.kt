package app.xl.sportappkmp.viewModels.exercisesTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.models.Exercise
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExerciseListState(
    val exercises: List<Exercise> = listOf(),
    val query: String = ""
)

class ExercisesScreenViewModel: ViewModel() {
    private val repository: ExercisesRepository = ExercisesRepositoryImpl

    private val _state = MutableStateFlow<ExerciseListState>(ExerciseListState())
    val state: CStateFlow<ExerciseListState> = _state.asStateFlow().cStateFlow()

    init {
        state
            .map { it.query }
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    repository.getAllExercises()
                } else {
                    repository.searchExercise(query)
                }
            }
            .onEach { exerciseList ->
                _state.update {
                    it.copy(exercises = exerciseList)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(query = query)
            }
        }
    }
}