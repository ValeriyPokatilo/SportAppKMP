package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object DemoWorkouts {
    fun createDemoWorkouts(fileManager: FileManager) {
        val workoutsRepository: WorkoutsRepository = WorkoutsRepositoryImpl.getInstance(fileManager)

        listOf(
            Workout(
                id = Uuid.random().toString(),
                title = "Gym Start",
                exerciseIds = listOf(
                    ExercisesStorage.elliptical.id,
                    ExercisesStorage.barbellBenchPress.id,
                    ExercisesStorage.pullUp.id,
                    ExercisesStorage.treadmill.id,
                    ExercisesStorage.smithMachineVerticalLegPress.id,
                    ExercisesStorage.machineShoulderPress.id,
                    ExercisesStorage.dumbbellLateralRaise.id,
                    ExercisesStorage.diamondPushUps.id,
                    ExercisesStorage.alternatingDumbbellCurl.id
                )
            ),
            Workout(
                id = Uuid.random().toString(),
                title = "Home Start",
                exerciseIds = listOf(
                    ExercisesStorage.jumpRope.id,
                    ExercisesStorage.pushUps.id,
                    ExercisesStorage.pullUp.id,
                    ExercisesStorage.bodyweightSquat.id
                )
            )
        ).forEach {
            CoroutineScope(Dispatchers.Default).launch {
                workoutsRepository.saveWorkout(it)
            }
        }
    }
}