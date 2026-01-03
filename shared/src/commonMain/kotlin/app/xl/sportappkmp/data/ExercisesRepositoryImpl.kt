package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

object ExercisesRepositoryImpl: ExercisesRepository {

    private val exerciseStateFlow = MutableStateFlow(ExercisesStorage.getExercises())

    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseStateFlow.asStateFlow()
    }

    override fun searchExercise(
        query: String,
        equipment: Equipment?,
        muscleGroup: MuscleGroup?,
        unitType: UnitType?
    ): Flow<List<Exercise>> {
        return exerciseStateFlow.map { currentList ->
            currentList.filter { exercise ->
                val matchesQuery = query.isBlank() || exercise.localizedTitle.lowercase()
                    .contains(query.lowercase())
                val matchesEquipment = equipment == null || exercise.equipment.contains(equipment)
                val matchesMuscleGroups = muscleGroup == null || exercise.muscleGroups.contains(muscleGroup)
                val matchesUnitType = unitType == null || exercise.unitType == unitType
                matchesQuery && matchesEquipment && matchesMuscleGroups && matchesUnitType
            }
        }
    }
}