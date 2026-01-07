package app.xl.sportappkmp.data

import android.content.Context

actual class FileManager(
    private val context: Context
) {

    actual suspend fun readText(fileName: String): String? =
        runCatching {
            context.openFileInput(fileName).bufferedReader().use { it.readText() }
        }.getOrNull()

    actual suspend fun writeText(fileName: String, text: String) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE)
            .bufferedWriter()
            .use { it.write(text) }
    }
}