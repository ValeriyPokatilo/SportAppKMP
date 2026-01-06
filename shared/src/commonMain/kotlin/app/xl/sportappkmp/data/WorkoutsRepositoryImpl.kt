package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object WorkoutsRepositoryImpl: WorkoutsRepository {

    private val workoutStateFlow: MutableStateFlow<List<Workout>> = MutableStateFlow(WorkoutsStorage.getWorkouts())

    override fun getAllWorkouts(): Flow<List<Workout>> {
        return workoutStateFlow.asStateFlow()
    }
}