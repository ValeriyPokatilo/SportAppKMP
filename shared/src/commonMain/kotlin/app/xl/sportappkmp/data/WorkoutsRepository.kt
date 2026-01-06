package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutsRepository {
    fun getAllWorkouts(): Flow<List<Workout>>
}