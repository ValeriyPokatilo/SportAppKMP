package app.xl.sportappkmp.models

import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.Localizer
import dev.icerock.moko.resources.StringResource

enum class Equipment {
    PLATE,
    MACHINE,
    CARDIO_MACHINE,
    KETTLEBELL,
    DUMBBELL,
    BARBELL,
    RESISTANCE_BAND,
    BALL,
    BENCH,
    PARALLEL_BARS,
    PULL_UP_BAR,
    ROPE,
    BODY_WEIGHT,
    OTHER
}

val Equipment.titleRes: StringResource
    get() = when (this) {
        Equipment.PLATE -> MR.strings.plate
        Equipment.MACHINE -> MR.strings.machine
        Equipment.CARDIO_MACHINE -> MR.strings.cardioMachine
        Equipment.KETTLEBELL -> MR.strings.kettlebell
        Equipment.DUMBBELL -> MR.strings.dumbbell
        Equipment.BARBELL -> MR.strings.barbell
        Equipment.RESISTANCE_BAND -> MR.strings.resistanceBand
        Equipment.BALL -> MR.strings.ball
        Equipment.BENCH -> MR.strings.bench
        Equipment.PARALLEL_BARS -> MR.strings.parallelBars
        Equipment.PULL_UP_BAR -> MR.strings.pullUpBar
        Equipment.ROPE -> MR.strings.rope
        Equipment.BODY_WEIGHT -> MR.strings.bodyweight
        Equipment.OTHER -> MR.strings.other
    }

fun Equipment.localizedTitle(
    localizer: Localizer
): String = localizer.get(titleRes, emptyList())