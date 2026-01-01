package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

enum class MuscleGroup {
    CHEST,
    BACK,
    LEGS,
    ARMS,
    SHOULDERS,
    CORE_ABS,
    NECK,
    CARDIO,
    OTHER
}

val MuscleGroup.titleRes: StringResource
    get() = when (this) {
        MuscleGroup.CHEST -> MR.strings.chest
        MuscleGroup.BACK -> MR.strings.back
        MuscleGroup.LEGS -> MR.strings.legs
        MuscleGroup.ARMS -> MR.strings.arms
        MuscleGroup.SHOULDERS -> MR.strings.shoulders
        MuscleGroup.CORE_ABS -> MR.strings.coreAbs
        MuscleGroup.NECK -> MR.strings.neck
        MuscleGroup.CARDIO -> MR.strings.cardio
        MuscleGroup.OTHER -> MR.strings.other
    }

fun MuscleGroup.localizedTitle(
    localizer: Localizer
): String = localizer.get(titleRes, emptyList())