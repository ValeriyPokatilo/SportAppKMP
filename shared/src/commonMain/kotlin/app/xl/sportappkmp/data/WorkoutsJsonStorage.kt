package app.xl.sportappkmp.data

import app.xl.sportappkmp.models.Workout
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WorkoutsJsonStorage(
    private val fileManager: FileManager
) {

    private val fileName = "workouts.json"

    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    suspend fun load(): List<Workout> {
        val text = fileManager.readText(fileName) ?: return emptyList()
        return json.decodeFromString(text)
    }

    suspend fun save(workouts: List<Workout>) {
        val text = json.encodeToString<List<Workout>>(workouts)
        fileManager.writeText(fileName, text)
    }
}