package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource

enum class UnitType(val titleRes: StringResource): LocalizedEnum {
    WITH_WEIGHT(MR.strings.withWeight),
    WITHOUT_WEUGHT(MR.strings.withoutWeight),
    TIMER(MR.strings.timer),
    DISTANCE(MR.strings.distance);

    override fun localizedTitle(
        localizer: Localizer
    ): String = localizer.get(titleRes, emptyList())

    fun allTitle(
        localizer: Localizer
    ): String = localizer.get(MR.strings.allUnitTypes, emptyList())

    companion object {
        val allCases: List<UnitType> = UnitType.entries
    }
}