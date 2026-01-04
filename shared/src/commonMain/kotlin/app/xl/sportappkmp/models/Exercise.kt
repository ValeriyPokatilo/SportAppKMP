package app.xl.sportappkmp.models

import app.xl.sportappkmp.utils.currentLanguageCode
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Exercise(
    val id: String = Uuid.random().toString(),
    val titleRu: String,
    val titleEn: String,
    val unitType: UnitType,
    val muscleGroups: List<MuscleGroup>,
    val equipments: List<Equipment>,
    val iconName: String,
    val imageName: String,
    val canEdit: Boolean
) {
    val localizedTitle: String
        get() = when (currentLanguageCode()) {
            "ru" -> titleRu
            else -> titleEn
        }
}
