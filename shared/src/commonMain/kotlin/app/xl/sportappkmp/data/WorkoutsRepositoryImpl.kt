package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class WorkoutsRepositoryImpl (
    fileManager: FileManager
) : WorkoutsRepository {

    private val storage = WorkoutsJsonStorage(fileManager)
    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())

    override fun getAllWorkouts(): Flow<List<Workout>> = _workouts.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            _workouts.value = storage.load()
        }
    }

    override suspend fun addWorkout(title: String, exerciseIds: List<String>) {
        val workout = Workout(
            id = Uuid.random().toString(),
            title = title,
            exerciseIds = exerciseIds
        )
        val updated = _workouts.value + workout
        _workouts.value = updated
        storage.save(updated)
    }

    suspend fun deleteWorkout(id: String) {
        val updated = _workouts.value.filterNot { it.id == id }
        _workouts.value = updated
        storage.save(updated)
    }
}
