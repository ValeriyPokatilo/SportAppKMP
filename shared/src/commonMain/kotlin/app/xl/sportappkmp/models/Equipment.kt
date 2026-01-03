package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource

enum class Equipment(val titleRes: StringResource): LocalizedEnum {
    PLATE(MR.strings.plate),
    MACHINE(MR.strings.machine),
    CARDIO_MACHINE(MR.strings.cardioMachine),
    KETTLEBELL(MR.strings.kettlebell),
    DUMBBELL(MR.strings.dumbbell),
    BARBELL(MR.strings.barbell),
    RESISTANCE_BAND(MR.strings.resistanceBand),
    BALL(MR.strings.ball),
    BENCH(MR.strings.bench),
    PARALLEL_BARS(MR.strings.parallelBars),
    PULL_UP_BAR(MR.strings.pullUpBar),
    ROPE(MR.strings.rope),
    BODY_WEIGHT(MR.strings.bodyweight),
    OTHER(MR.strings.other);

    override fun localizedTitle(
        localizer: Localizer
    ): String = localizer.get(titleRes, emptyList())

    fun allTitle(
        localizer: Localizer
    ): String = localizer.get(MR.strings.allEquipment, emptyList())

    companion object {
        val allCases: List<Equipment> = entries
    }
}