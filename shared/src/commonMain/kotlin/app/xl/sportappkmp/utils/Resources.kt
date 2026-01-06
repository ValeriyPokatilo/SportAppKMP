package app.xl.sportappkmp.utils

import app.xl.sportappkmp.MR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.getImageByFileName

fun getImageByFileName(name: String): ImageResource {
    val fallbackImage = if (name.contains("Icon")) {
        MR.images.placeholderIcon
    } else {
        MR.images.placeholder
    }
    return MR.images.getImageByFileName(name) ?: fallbackImage
}

