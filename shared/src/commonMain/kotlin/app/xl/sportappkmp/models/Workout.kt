package app.xl.sportappkmp.models

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class Workout(
    val id: String = Uuid.random().toString(),
    val title: String,
    val exerciseIds: List<String>
)
