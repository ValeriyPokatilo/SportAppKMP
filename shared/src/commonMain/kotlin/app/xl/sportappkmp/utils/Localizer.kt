package app.xl.sportappkmp.utils

import dev.icerock.moko.resources.StringResource

expect class Localizer {
    fun get(id: StringResource, args: List<Any>): String
}