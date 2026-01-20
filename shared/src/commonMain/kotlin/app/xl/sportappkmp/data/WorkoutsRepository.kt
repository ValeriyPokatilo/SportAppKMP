package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutsRepository {
    fun getAllWorkouts(): Flow<List<Workout>>
    suspend fun addWorkout(title: String, exerciseIds: List<String>)
    suspend fun getWorkout(id: String?): Workout
    suspend fun deleteWorkout(id: String)
    suspend fun saveWorkout(workout: Workout)
}