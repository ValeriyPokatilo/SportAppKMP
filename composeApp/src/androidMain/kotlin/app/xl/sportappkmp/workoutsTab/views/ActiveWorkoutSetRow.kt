package app.xl.sportappkmp.workoutsTab.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.sportappkmp.MR

@Composable
fun ActiveWorkoutSetRow(
    reps: String,
    weight: String?,
    date: String,
    isCurrent: Boolean = true
) {
    val context = LocalContext.current.applicationContext

    val backgroundColor: Color
    val textColor: Color

    if (isCurrent) {
        backgroundColor = Color(MR.colors.baseGreen.getColor(context)).copy(alpha = 0.1f)
        textColor = Color.DarkGray
    } else {
        backgroundColor = Color(MR.colors.baseGray.getColor(context)).copy(alpha = 0.8f)
        textColor = Color.Black.copy(alpha = 0.4f)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = reps,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = textColor
            )

            weight?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = textColor
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = date,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = Color(MR.colors.baseBlue.getColor(context))
            )
        }
    }
}