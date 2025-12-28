package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Exercise
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun searchExercise(query: String): Flow<List<Exercise>>
}