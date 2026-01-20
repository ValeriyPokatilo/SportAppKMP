package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import app.xl.sportappkmp.utils.atomicGate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.concurrent.Volatile
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class WorkoutsRepositoryImpl private constructor(
    fileManager: FileManager
) : WorkoutsRepository {

    private val storage = WorkoutsJsonStorage(fileManager)
    private val workoutsStateFlow = MutableStateFlow<List<Workout>>(emptyList())

    override fun getAllWorkouts(): Flow<List<Workout>> = workoutsStateFlow.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            workoutsStateFlow.value = storage.load()
        }
    }

    override suspend fun addWorkout(title: String, exerciseIds: List<String>) {
        val workout = Workout(
            id = Uuid.random().toString(),
            title = title,
            exerciseIds = exerciseIds
        )
        val updated = workoutsStateFlow.value + workout
        workoutsStateFlow.value = updated
        storage.save(updated)
    }

    override suspend fun getWorkout(id: String?): Workout {
        return workoutsStateFlow.value.first {
            it.id == id
        }
    }

    override suspend fun deleteWorkout(id: String) {
        val updated = workoutsStateFlow.value.filterNot { it.id == id }
        workoutsStateFlow.value = updated
        storage.save(updated)
    }

    override suspend fun saveWorkout(workout: Workout) {
        var updatedList: List<Workout> = emptyList()

        workoutsStateFlow.update { current ->
            val index = current.indexOfFirst { it.id == workout.id }

            updatedList = if (index >= 0) {
                current.toMutableList().apply {
                    this[index] = workout
                }
            } else {
                current + workout
            }
            updatedList
        }

        storage.save(updatedList)
    }

    @OptIn(InternalCoroutinesApi::class)
    companion object {
        @Volatile
        private var INSTANCE: WorkoutsRepositoryImpl? = null

        private val lock = Any()

        fun getInstance(fileManager: FileManager): WorkoutsRepositoryImpl {
            val existing = INSTANCE
            if (existing != null) return existing

            return atomicGate(lock) {
                INSTANCE ?: WorkoutsRepositoryImpl(fileManager).also { INSTANCE = it }
            }
        }
    }
}
