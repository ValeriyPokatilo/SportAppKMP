package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.xl.sportappkmp.MR

@Composable
fun FilterBarButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(MR.colors.baseGreen.getColor(context)),
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = title,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis
        )
    }
}