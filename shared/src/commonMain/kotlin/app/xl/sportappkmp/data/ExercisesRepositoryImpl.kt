package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

object ExercisesRepositoryImpl: ExercisesRepository {

    private val exerciseStateFlow = MutableStateFlow(ExercisesStorage.getExercises())

    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseStateFlow.asStateFlow()
    }

    override fun searchExercise(query: String): Flow<List<Exercise>> {
        return exerciseStateFlow.map { currentList ->
            currentList.filter { exercise ->
                // TODO: - use localizedTitle
                exercise.titleRu.lowercase().contains(query.lowercase()) ||
                        exercise.titleEn.lowercase().contains(query.lowercase())
            }
        }
    }
}