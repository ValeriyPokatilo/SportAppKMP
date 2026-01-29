package app.xl.sportappkmp.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun formatSetDate(isoDate: String, isCurrent: Boolean): String {
    val dt = Instant.parse(isoDate).toLocalDateTime(TimeZone.currentSystemDefault())

    return if (isCurrent)
        "%02d:%02d".format(dt.hour, dt.minute)
    else
        "%02d.%02d.%04d".format(dt.dayOfMonth, dt.monthNumber, dt.year)
}