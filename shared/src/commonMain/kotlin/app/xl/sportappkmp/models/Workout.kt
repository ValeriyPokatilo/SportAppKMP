package app.xl.sportappkmp.models

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Workout(
    val id: String = Uuid.random().toString(),
    val title: String,
    val exerciseIds: List<String>
)
