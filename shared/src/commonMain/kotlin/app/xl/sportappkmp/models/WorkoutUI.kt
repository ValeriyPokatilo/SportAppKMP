package app.xl.sportappkmp.models

data class WorkoutUI(
    val id: String,
    val title: String,
    val exercises: List<Exercise>
) {
    val exerciseTitles: String
        get() = exercises.joinToString(" • ") { it.localizedTitle }
}
