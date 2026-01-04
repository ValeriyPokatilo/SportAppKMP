package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType

object ExercisesStorage {
    val treadmill = Exercise(
        id = "3E211887-3B97-4B10-9F30-62F607B4C86A",
        titleRu = "Беговая дорожка",
        titleEn = "Treadmill",
        unitType = UnitType.DISTANCE,
        muscleGroups = listOf(
            MuscleGroup.LEGS,
            MuscleGroup.CARDIO,
            MuscleGroup.CORE_ABS
        ),
        equipments = listOf(Equipment.BODY_WEIGHT),
        iconName = "treadmillImageIcon",
        imageName = "treadmillImage",
        canEdit = false
    )

    val smithMachineVerticalLegPress = Exercise(
        id = "6C160CFD-2989-4BF5-8E21-4065D2E7FB4F",
        titleRu = "Вертикальный жим ногами",
        titleEn = "Smith Machine Vertical Leg Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.LEGS),
        equipments = listOf(Equipment.MACHINE),
        iconName = "smithMachineVerticalLegPressIcon",
        imageName = "smithMachineVerticalLegPressImage",
        canEdit = false
    )

    val machineShoulderPress = Exercise(
        id = "0C208CC7-07BF-4832-8B79-B0705C0ED0A3",
        titleRu = "Жим на плечи в тренажёре сидя",
        titleEn = "Seated Machine Shoulder Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipments = listOf(Equipment.MACHINE),
        iconName = "machineShoulderPressIcon",
        imageName = "machineShoulderPressImage",
        canEdit = false
    )

    val barbellBenchPress = Exercise(
        id = "8B8D801D-4C49-4EF0-8B79-2432480459C6",
        titleRu = "Жим штанги лёжа",
        titleEn = "Barbell Bench Press",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS, MuscleGroup.SHOULDERS),
        equipments = listOf(Equipment.BARBELL, Equipment.BENCH),
        iconName = "barbellBenchPressIcon",
        imageName = "barbellBenchPressImage",
        canEdit = false
    )

    val dumbbellLateralRaise = Exercise(
        id = "70D0B209-1B9E-4032-B442-E7F2C05AB4EB",
        titleRu = "Махи гантелями в стороны",
        titleEn = "Dumbbell Lateral Raise",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipments = listOf(Equipment.DUMBBELL),
        iconName = "dumbbellLateralRaiseIcon",
        imageName = "dumbbellLateralRaiseImage",
        canEdit = false
    )

    val pushUps = Exercise(
        id = "B807A6BE-843D-497C-8AB2-BE4AD1336BA9",
        titleRu = "Отжимания",
        titleEn = "Push-Ups",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS, MuscleGroup.SHOULDERS),
        equipments = listOf(Equipment.BODY_WEIGHT),
        iconName = "pushUpsIcon",
        imageName = "pushUpsImage",
        canEdit = false
    )

    val diamondPushUps = Exercise(
        id = "3C2794EF-2816-495E-A277-10E52928C4C9",
        titleRu = "Отжимания бриллиантом",
        titleEn = "Diamond Push-Ups",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.ARMS, MuscleGroup.CHEST),
        equipments = listOf(Equipment.BODY_WEIGHT),
        iconName = "diamondPushUpsIcon",
        imageName = "diamondPushUpsImage",
        canEdit = false
    )

    val pullUp = Exercise(
        id = "10FB8209-8F87-44E8-A018-CAD7EAA4BE3C",
        titleRu = "Подтягивания",
        titleEn = "Pull-Up",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.BACK),
        equipments = listOf(Equipment.PULL_UP_BAR),
        iconName = "pullUpIcon",
        imageName = "pullUpImage",
        canEdit = false
    )

    val alternatingDumbbellCurl = Exercise(
        id = "CF858CA9-DBBE-40CE-A500-3FFC08D5A1F0",
        titleRu = "Попеременный подъем гантелей на бицепс",
        titleEn = "Alternating Dumbbell Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipments = listOf(Equipment.DUMBBELL),
        iconName = "alternatingDumbbellCurlIcon",
        imageName = "alternatingDumbbellCurlImage",
        canEdit = false
    )

    val bodyweightSquat = Exercise(
        id = "CAF147AA-585D-4CC7-A88D-991DE63BEA9B",
        titleRu = "Приседания без веса",
        titleEn = "Bodyweight Squat",
        unitType = UnitType.WITHOUT_WEUGHT,
        muscleGroups = listOf(MuscleGroup.LEGS),
        equipments = listOf(Equipment.BODY_WEIGHT),
        iconName = "bodyweightSquatIcon",
        imageName = "bodyweightSquatImage",
        canEdit = false
    )

    val jumpRope = Exercise(
        id = "E3E81D1B-4AD2-42E0-9F37-A4E8D9B3EF37",
        titleRu = "Прыжки на скакалке",
        titleEn = "Jump Rope",
        unitType = UnitType.TIMER,
        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.CARDIO),
        equipments = listOf(Equipment.OTHER),
        iconName = "jumpRopeIcon",
        imageName = "jumpRopeImage",
        canEdit = false
    )

    val cableTricepPushdown = Exercise(
        id = "65202BE8-E925-4E58-8D51-BE99860C0AC6",
        titleRu = "Разгибание рук на верхнем блоке стоя",
        titleEn = "Cable Tricep Pushdown",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipments = listOf(Equipment.MACHINE),
        iconName = "cableTricepPushdownIcon",
        imageName = "cableTricepPushdownImage",
        canEdit = false
    )

    val seatedDumbbellBicepCurl = Exercise(
        id = "C8C24498-CE36-404D-BC65-8C09C997329E",
        titleRu = "Сгибание рук с гантелями сидя",
        titleEn = "Seated Dumbbell Bicep Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipments = listOf(Equipment.DUMBBELL, Equipment.BENCH),
        iconName = "seatedDumbbellBicepCurlIcon",
        imageName = "seatedDumbbellBicepCurlImage",
        canEdit = false
    )

    val dumbbellBicepCurl = Exercise(
        id = "58C22EF2-B8C9-4CD1-9586-19338E5C2FDA",
        titleRu = "Сгибания рук с гантелями стоя",
        titleEn = "Dumbbell Bicep Curl",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.ARMS),
        equipments = listOf(Equipment.DUMBBELL),
        iconName = "dumbbellBicepCurlIcon",
        imageName = "dumbbellBicepCurlImage",
        canEdit = false
    )

    val seatedDumbbellLateralRaise = Exercise(
        id = "B46E827B-AEEA-4A14-AAE2-EB4571874760",
        titleRu = "Сидячие махи гантелями в стороны",
        titleEn = "Seated Dumbbell Lateral Raise",
        unitType = UnitType.WITH_WEIGHT,
        muscleGroups = listOf(MuscleGroup.SHOULDERS),
        equipments = listOf(Equipment.DUMBBELL),
        iconName = "seatedDumbbellLateralRaiseIcon",
        imageName = "seatedDumbbellLateralRaiseImage",
        canEdit = false
    )

    val elliptical = Exercise(
        id = "B1E43C34-8F16-4E36-A8D6-2CC951950C1F",
        titleRu = "Эллипс",
        titleEn = "Elliptical",
        unitType = UnitType.DISTANCE,
        muscleGroups = listOf(MuscleGroup.CARDIO, MuscleGroup.LEGS, MuscleGroup.ARMS),
        equipments = listOf(Equipment.CARDIO_MACHINE),
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