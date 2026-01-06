package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

enum class Equipment(val titleRes: StringResource, val imageRes: ImageResource): LocalizedEnum {
    PLATE(MR.strings.plate, MR.images.eqPlate),
    MACHINE(MR.strings.machine, MR.images.eqMachine),
    CARDIO_MACHINE(MR.strings.cardioMachine, MR.images.eqCardioMachine),
    KETTLEBELL(MR.strings.kettlebell, MR.images.eqKettlebell),
    DUMBBELL(MR.strings.dumbbell, MR.images.eqDumbbell),
    BARBELL(MR.strings.barbell, MR.images.eqBarbell),
    RESISTANCE_BAND(MR.strings.resistanceBand, MR.images.eqResistanceBand),
    BALL(MR.strings.ball, MR.images.eqBall),
    BENCH(MR.strings.bench, MR.images.eqBench),
    PARALLEL_BARS(MR.strings.parallelBars, MR.images.eqParallelBars),
    PULL_UP_BAR(MR.strings.pullUpBar, MR.images.eqParallelBars),
    ROPE(MR.strings.rope, MR.images.eqRope),
    BODY_WEIGHT(MR.strings.bodyweight, MR.images.eqBodyweight),
    OTHER(MR.strings.other, MR.images.eqOther);

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