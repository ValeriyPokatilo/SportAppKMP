package app.xl.sportappkmp.data

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
actual class FileManager {

    private val directory =
        NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory,
            NSUserDomainMask,
            true
        ).first() as String

    actual suspend fun readText(fileName: String): String? {
        val path = "$directory/$fileName"
        return NSString.stringWithContentsOfFile(
            path,
            encoding = NSUTF8StringEncoding,
            error = null
        ) as String?
    }

    actual suspend fun writeText(fileName: String, text: String) {
        val path = "$directory/$fileName"
        (text as NSString).writeToFile(
            path,
            atomically = true,
            encoding = NSUTF8StringEncoding,
            error = null
        )
    }
}
