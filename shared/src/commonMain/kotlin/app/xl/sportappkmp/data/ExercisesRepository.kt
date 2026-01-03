package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun searchExercise(
        query: String,
        equipment: Equipment? = null,
        muscleGroup: MuscleGroup? = null,
        unitType: UnitType? = null
    ): Flow<List<Exercise>>
}