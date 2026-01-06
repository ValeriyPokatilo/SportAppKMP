package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

enum class MuscleGroup(
    val titleRes: StringResource,
    val imageRes: ImageResource
): LocalizedEnum {
    CHEST(MR.strings.chest, MR.images.mgChest),
    BACK(MR.strings.back, MR.images.mgBack),
    LEGS(MR.strings.legs, MR.images.mgLegs),
    ARMS(MR.strings.arms, MR.images.mgArm),
    SHOULDERS(MR.strings.shoulders, MR.images.mgShoulders),
    CORE_ABS(MR.strings.coreAbs, MR.images.mgAbs),
    NECK(MR.strings.neck, MR.images.mgNeck),
    CARDIO(MR.strings.cardio, MR.images.mgCardio),
    OTHER(MR.strings.other, MR.images.mgOther);

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

