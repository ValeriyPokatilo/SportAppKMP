package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout

object WorkoutsStorage {
    val wrk1 = Workout(
        title = "Gym Start",
        exerciseIds = listOf(
            ExercisesStorage.elliptical.id,
            ExercisesStorage.barbellBenchPress.id,
            ExercisesStorage.pullUp.id,
            ExercisesStorage.alternatingDumbbellCurl.id
        )
    )

    val wrk2 = Workout(
        title = "Home Start",
        exerciseIds = listOf(
            ExercisesStorage.jumpRope.id,
            ExercisesStorage.pushUps.id,
            ExercisesStorage.pullUp.id,
            ExercisesStorage.bodyweightSquat.id
        )
    )

    fun getWorkouts(): List<Workout> {
        return listOf(wrk1, wrk2)
    }
}
