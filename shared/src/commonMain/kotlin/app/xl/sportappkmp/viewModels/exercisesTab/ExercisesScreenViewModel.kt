package app.xl.sportappkmp.viewModels.exercisesTab

import app.xl.sportappkmp.data.ExercisesRepository
import app.xl.sportappkmp.data.ExercisesRepositoryImpl
import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class ExerciseListState(
    val exercises: List<Exercise> = listOf(),
    val query: String = "",
    val equipment: Equipment? = null,
    val muscleGroup: MuscleGroup? = null,
    val unitType: UnitType? = null
)

class ExercisesScreenViewModel: ViewModel() {
    private val repository: ExercisesRepository = ExercisesRepositoryImpl

    private val _state = MutableStateFlow<ExerciseListState>(ExerciseListState())
    val state: CStateFlow<ExerciseListState> = _state.asStateFlow().cStateFlow()

    private val _activeSheet = MutableStateFlow<FilterSheetType>(FilterSheetType.NONE)
    val activeSheet: CStateFlow<FilterSheetType> = _activeSheet.asStateFlow().cStateFlow()

    init {
        _state
            .map { state -> state }
            .flatMapLatest { state ->
                val query = state.query
                val equipment = state.equipment
                val muscleGroup = state.muscleGroup
                val unitType = state.unitType

                val hasActiveFilter = query.isNotBlank()
                        || equipment != null
                        || muscleGroup != null
                        || unitType != null

                if (hasActiveFilter) {
                    repository.searchExercise(
                        query = query,
                        equipment = equipment,
                        muscleGroup = muscleGroup,
                        unitType = unitType
                    )
                } else {
                    repository.getAllExercises()
                }
            }
            .onEach { exerciseList ->
                _state.update { it.copy(exercises = exerciseList) }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        _state.update {
            it.copy(query = query)
        }
    }

    fun onEquipmentChange(equipment: Equipment?) {
        _state.update {
            it.copy(equipment = equipment)
        }
    }

    fun onMuscleGroupChange(muscleGroup: MuscleGroup?) {
        _state.update {
            it.copy(muscleGroup = muscleGroup)
        }
    }

    fun onUnitTypeChange(unitType: UnitType?) {
        _state.update {
            it.copy(unitType = unitType)
        }
    }

    fun resetQuery() {
        _state.update {
            it.copy(query = "")
        }
    }

    fun resetFilters() {
        _state.update {
            it.copy(equipment = null, muscleGroup = null, unitType = null)
        }
    }

    fun openSheet(type: FilterSheetType) {
        _activeSheet.value = type
    }

    fun closeSheet() {
        _activeSheet.value = FilterSheetType.NONE
    }
}

enum class FilterSheetType(val id: String) {
    EQUIPMENT("equipment"),
    MUSCLE("muscle"),
    UNIT_TYPE("unitType"),
    NONE("none")
}