package app.xl.sportappkmp.data

import app.xl.ExerciseSet
import kotlinx.coroutines.flow.Flow


interface SetsRepository {
    suspend fun addSet(set: ExerciseSet)
    fun fetchCurrentSets(): Flow<List<ExerciseSet>>
    suspend fun fetchPreviousSets(exerciseId: String): List<ExerciseSet>

    // Debug
    suspend fun shiftAllSetsOneDayBack()
}