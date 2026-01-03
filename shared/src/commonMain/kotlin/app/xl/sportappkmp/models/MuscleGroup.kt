package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource

enum class MuscleGroup(val titleRes: StringResource): LocalizedEnum {
    CHEST(MR.strings.chest),
    BACK(MR.strings.back),
    LEGS(MR.strings.legs),
    ARMS(MR.strings.arms),
    SHOULDERS(MR.strings.shoulders),
    CORE_ABS(MR.strings.coreAbs),
    NECK(MR.strings.neck),
    CARDIO(MR.strings.cardio),
    OTHER(MR.strings.other);

    override fun localizedTitle(
        localizer: Localizer
    ): String = localizer.get(titleRes, emptyList())

    fun allTitle(
        localizer: Localizer
    ): String = localizer.get(MR.strings.allMuscles, emptyList())

    companion object {
        val allCases: List<MuscleGroup> = MuscleGroup.entries
    }
}

