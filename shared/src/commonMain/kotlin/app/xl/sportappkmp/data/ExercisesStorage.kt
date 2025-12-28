package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object ExercisesStorage {
    val treadmill = Exercise(
        id = Uuid.parse("3E211887-3B97-4B10-9F30-62F607B4C86A"),
        titleRu = "Беговая дорожка",
        titleEn = "Treadmill",
        unitType = UnitType.DISTANCE,
        muscleGroups = listOf(
            MuscleGroup.LEGS,
            MuscleGroup.CARDIO,
            MuscleGroup.CORE_ABS
        ),
        equipment = listOf(Equipment.BODY_WEIGHT),
        iconName = "treadmillImageIcon",
        imageName = "treadmillImage",
        canEdit = false
    )

    val smithMachineVerticalLegPress = Exercise(
        id = Uuid.parse("6C160CFD-2989-4BF5-8E21-4065D2E7FB4F"),
        titleRu = "Вертикальный жим ногами",
        titleEn = "Smith Machine Vertical Leg Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.LEGS),
        equipment = listOf(Equipment.MACHINE),
        iconName = "smithMachineVerticalLegPressIcon",
        imageName = "smithMachineVerticalLegPressImage",
        canEdit = false
    )

    val machineShoulderPress = Exercise(
        id = Uuid.parse("0C208CC7-07BF-4832-8B79-B0705C0ED0A3"),
        titleRu = "Жим на плечи в тренажёре сидя",
        titleEn = "Seated Machine Shoulder Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipment = listOf(Equipment.MACHINE),
        iconName = "machineShoulderPressIcon",
        imageName = "machineShoulderPressImage",
        canEdit = false
    )

    val barbellBenchPress = Exercise(
        id = Uuid.parse("8B8D801D-4C49-4EF0-8B79-2432480459C6"),
        titleRu = "Жим штанги лёжа",
        titleEn = "Barbell Bench Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS, MuscleGroup.SHOULDERS),
        equipment = listOf(Equipment.BARBELL, Equipment.BENCH),
        iconName = "barbellBenchPressIcon",
        imageName = "barbellBenchPressImage",
        canEdit = false
    )

    val dumbbellLateralRaise = Exercise(
        id = Uuid.parse("70D0B209-1B9E-4032-B442-E7F2C05AB4EB"),
        titleRu = "Махи гантелями в стороны",
        titleEn = "Dumbbell Lateral Raise",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipment = listOf(Equipment.DUMBBELL),
        iconName = "dumbbellLateralRaiseIcon",
        imageName = "dumbbellLateralRaiseImage",
        canEdit = false
    )

    val pushUps = Exercise(
        id = Uuid.parse("B807A6BE-843D-497C-8AB2-BE4AD1336BA9"),
        titleRu = "Отжимания",
        titleEn = "Push-Ups",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS, MuscleGroup.SHOULDERS),
        equipment = listOf(Equipment.BODY_WEIGHT),
        iconName = "pushUpsIcon",
        imageName = "pushUpsImage",
        canEdit = false
    )

    val diamondPushUps = Exercise(
        id = Uuid.parse("3C2794EF-2816-495E-A277-10E52928C4C9"),
        titleRu = "Отжимания бриллиантом",
        titleEn = "Diamond Push-Ups",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.ARMS, MuscleGroup.CHEST),
        equipment = listOf(Equipment.BODY_WEIGHT),
        iconName = "diamondPushUpsIcon",
        imageName = "diamondPushUpsImage",
        canEdit = false
    )

    val pullUp = Exercise(
        id = Uuid.parse("10FB8209-8F87-44E8-A018-CAD7EAA4BE3C"),
        titleRu = "Подтягивания",
        titleEn = "Pull-Up",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.BACK),
        equipment = listOf(Equipment.PULL_UP_BAR),
        iconName = "pullUpIcon",
        imageName = "pullUpImage",
        canEdit = false
    )

    val alternatingDumbbellCurl = Exercise(
        id = Uuid.parse("CF858CA9-DBBE-40CE-A500-3FFC08D5A1F0"),
        titleRu = "Попеременный подъем гантелей на бицепс",
        titleEn = "Alternating Dumbbell Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipment = listOf(Equipment.DUMBBELL),
        iconName = "alternatingDumbbellCurlIcon",
        imageName = "alternatingDumbbellCurlImage",
        canEdit = false
    )

    val bodyweightSquat = Exercise(
        id = Uuid.parse("CAF147AA-585D-4CC7-A88D-991DE63BEA9B"),
        titleRu = "Приседания без веса",
        titleEn = "Bodyweight Squat",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.LEGS),
        equipment = listOf(Equipment.BODY_WEIGHT),
        iconName = "bodyweightSquatIcon",
        imageName = "bodyweightSquatImage",
        canEdit = false
    )

    val jumpRope = Exercise(
        id = Uuid.parse("E3E81D1B-4AD2-42E0-9F37-A4E8D9B3EF37"),
        titleRu = "Прыжки на скакалке",
        titleEn = "Jump Rope",
        unitType = UnitType.TIMER,
        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.CARDIO),
        equipment = listOf(Equipment.OTHER),
        iconName = "jumpRopeIcon",
        imageName = "jumpRopeImage",
        canEdit = false
    )

    val cableTricepPushdown = Exercise(
        id = Uuid.parse("65202BE8-E925-4E58-8D51-BE99860C0AC6"),
        titleRu = "Разгибание рук на верхнем блоке стоя",
        titleEn = "Cable Tricep Pushdown",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipment = listOf(Equipment.MACHINE),
        iconName = "cableTricepPushdownIcon",
        imageName = "cableTricepPushdownImage",
        canEdit = false
    )

    val seatedDumbbellBicepCurl = Exercise(
        id = Uuid.parse("C8C24498-CE36-404D-BC65-8C09C997329E"),
        titleRu = "Сгибание рук с гантелями сидя",
        titleEn = "Seated Dumbbell Bicep Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipment = listOf(Equipment.DUMBBELL, Equipment.BENCH),
        iconName = "seatedDumbbellBicepCurlIcon",
        imageName = "seatedDumbbellBicepCurlImage",
        canEdit = false
    )

    val dumbbellBicepCurl = Exercise(
        id = Uuid.parse("58C22EF2-B8C9-4CD1-9586-19338E5C2FDA"),
        titleRu = "Сгибания рук с гантелями стоя",
        titleEn = "Dumbbell Bicep Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipment = listOf(Equipment.DUMBBELL),
        iconName = "dumbbellBicepCurlIcon",
        imageName = "dumbbellBicepCurlImage",
        canEdit = false
    )

    val seatedDumbbellLateralRaise = Exercise(
        id = Uuid.parse("B46E827B-AEEA-4A14-AAE2-EB4571874760"),
        titleRu = "Сидячие махи гантелями в стороны",
        titleEn = "Seated Dumbbell Lateral Raise",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipment = listOf(Equipment.DUMBBELL),
        iconName = "seatedDumbbellLateralRaiseIcon",
        imageName = "seatedDumbbellLateralRaiseImage",
        canEdit = false
    )

    val elliptical = Exercise(
        id = Uuid.parse("B1E43C34-8F16-4E36-A8D6-2CC951950C1F"),
        titleRu = "Эллипс",
        titleEn = "Elliptical",
        unitType = UnitType.DISTANCE,
        muscleGroups = listOf(MuscleGroup.CARDIO, MuscleGroup.LEGS, MuscleGroup.ARMS),
        equipment = listOf(Equipment.CARDIO_MACHINE),
        iconName = "ellipticalIcon",
        imageName = "ellipticalImage",
        canEdit = false
    )

    fun getExercises(): List<Exercise> {
        return listOf(
            treadmill,
            smithMachineVerticalLegPress,
            machineShoulderPress,
            barbellBenchPress,
            dumbbellLateralRaise,
            pushUps,
            diamondPushUps,
            pullUp,
            alternatingDumbbellCurl,
            bodyweightSquat,
            jumpRope,
            cableTricepPushdown,
            seatedDumbbellBicepCurl,
            dumbbellBicepCurl,
            seatedDumbbellLateralRaise,
            elliptical
        )
    }
}