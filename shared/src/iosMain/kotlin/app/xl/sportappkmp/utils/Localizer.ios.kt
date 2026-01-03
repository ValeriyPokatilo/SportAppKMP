package app.xl.sportappkmp.utils

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

actual class Localizer {
    actual fun get(id: StringResource): String {
        return get(id, emptyList())
    }

    actual fun getOpt(id: StringResource?): String? {
        if (id == null) return null

        return getOpt(id, emptyList())
    }

    actual fun get(id: StringResource, args: List<Any>): String {
        return if (args.isEmpty()) {
            StringDesc.Resource(id).localized()
        } else {
            id.format(*args.toTypedArray()).localized()
        }
    }

    actual fun getOpt(id: StringResource?, args: List<Any>): String? {
        if (id == null) return null

        return if (args.isEmpty()) {
            StringDesc.Resource(id).localized()
        } else {
            id.format(*args.toTypedArray()).localized()
        }
    }
}
