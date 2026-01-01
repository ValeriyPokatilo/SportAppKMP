package app.xl.sportappkmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.StringResource

@Composable
fun localizer(id: StringResource, vararg args: Any): String {
    return Localizer(LocalContext.current).get(id, args.toList())
}