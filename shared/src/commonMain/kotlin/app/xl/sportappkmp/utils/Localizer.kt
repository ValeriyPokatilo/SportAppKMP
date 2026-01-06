package app.xl.sportappkmp.utils

import dev.icerock.moko.resources.StringResource

expect class Localizer {
    fun get(id: StringResource): String
    fun getOpt(id: StringResource?): String?
    fun get(id: StringResource, args: List<Any> = emptyList()): String
    fun getOpt(id: StringResource?, args: List<Any> = emptyList()): String?
}