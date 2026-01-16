package app.xl.sportappkmp.commonViews

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import app.xl.sportappkmp.MR

@Composable
fun FloatingGreenButton(
    onClick: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(MR.colors.baseGreen.getColor(context)),
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add workout"
        )
    }
}