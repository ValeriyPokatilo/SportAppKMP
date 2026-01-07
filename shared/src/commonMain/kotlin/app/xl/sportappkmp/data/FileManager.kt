package app.xl.sportappkmp.data

expect class FileManager {
    suspend fun readText(fileName: String): String?
    suspend fun writeText(fileName: String, text: String)
}