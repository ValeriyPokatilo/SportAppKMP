package app.xl.sportappkmp.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun formatSetDate(isoDate: String, isCurrent: Boolean): String {
    val dt = Instant.parse(isoDate).toLocalDateTime(TimeZone.currentSystemDefault())

    return if (isCurrent) {
        "${dt.hour.toString().padStart(2, '0')}:${dt.minute.toString().padStart(2, '0')}"
    } else {
        "${dt.dayOfMonth.toString().padStart(2, '0')}.${dt.monthNumber.toString().padStart(2, '0')}.${dt.year}"
    }
}