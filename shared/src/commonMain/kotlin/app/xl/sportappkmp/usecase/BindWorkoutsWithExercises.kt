package app.xl.sportappkmp.usecase

import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.Workout
import app.xl.sportappkmp.models.WorkoutUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun bindWorkoutsWithExercises(
    workouts: List<Workout>,
    exercises: List<Exercise>
): List<WorkoutUI> {

    val exercisesMap = exercises.associateBy { it.id }

    return workouts.map { workout ->
        val boundExercises = workout.exerciseIds
            .mapNotNull { exercisesMap[it] }

        WorkoutUI(
            id = workout.id,
            title = workout.title,
            exercises = boundExercises
        )
    }
}

fun bindWorkoutsWithExercises(
    workoutsFlow: Flow<List<Workout>>,
    exercisesFlow: Flow<List<Exercise>>
): Flow<List<WorkoutUI>> =
    combine(workoutsFlow, exercisesFlow) { workouts, exercises ->
        bindWorkoutsWithExercises(workouts, exercises)
    }