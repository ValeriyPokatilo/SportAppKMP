package app.xl.sportappkmp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.xl.AppDatabase
import app.xl.ExerciseSet
import app.xl.sportappkmp.utils.atomicGate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlin.concurrent.Volatile
import kotlin.coroutines.CoroutineContext

class SetsRepositoryImpl private constructor(
    db: AppDatabase,
    private val coroutineContext: CoroutineContext
): SetsRepository {

    private val setsQuery = db.exerciseSetQueries

    override suspend fun addSet(set: ExerciseSet) {
        setsQuery.addSet(
            id = set.id,
            exerciseId = set.exerciseId,
            reps = set.reps,
            weight = set.weight,
            timeStamp = set.timeStamp
        )
    }

    override fun fetchCurrentSets(): Flow<List<ExerciseSet>> {
        return setsQuery.fetchCurrentSets()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override suspend fun fetchPreviousSets(exerciseId: String): List<ExerciseSet> {
        return setsQuery
            .fetchPreviousSets(exerciseId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .first()
    }

    override suspend fun shiftAllSetsOneDayBack() {
        setsQuery.shiftAllSetsOneDayBack()
    }

    @OptIn(InternalCoroutinesApi::class)
    companion object {
        @Volatile
        private var INSTANCE: SetsRepositoryImpl? = null

        private val lock = Any()

        fun getInstance(db: AppDatabase, coroutineContext: CoroutineContext): SetsRepositoryImpl {
            val existing = INSTANCE
            if (existing != null) return existing

            return atomicGate(lock) {
                INSTANCE ?: SetsRepositoryImpl(db, coroutineContext).also { INSTANCE = it }
            }
        }
    }
}