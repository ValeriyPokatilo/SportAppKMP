package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource

enum class UnitType {
    WITH_WEIGHT,
    WITHOUT_WEUGHT,
    TIMER,
    DISTANCE
}

val UnitType.titleRes: StringResource
    get() = when (this) {
        UnitType.WITH_WEIGHT -> MR.strings.withWeight
        UnitType.WITHOUT_WEUGHT -> MR.strings.withoutWeight
        UnitType.TIMER -> MR.strings.timer
        UnitType.DISTANCE -> MR.strings.distance
    }

fun UnitType.localizedTitle(
    localizer: Localizer
): String = localizer.get(titleRes, emptyList())