package app.xl.sportappkmp.models

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Exercise(
    val id: Uuid = Uuid.random(),
    val titleRu: String,
    val titleEn: String,
    val unitType: UnitType,
    val muscleGroups: List<MuscleGroup>,
    val equipment: List<Equipment>,
    val iconName: String,
    val imageName: String,
    val canEdit: Boolean
)
