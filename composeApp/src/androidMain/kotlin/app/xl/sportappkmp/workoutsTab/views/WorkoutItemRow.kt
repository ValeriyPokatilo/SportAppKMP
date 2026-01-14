package app.xl.sportappkmp.workoutsTab.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.sportappkmp.models.WorkoutUI

@Composable
fun WorkoutItemRow(
    modifier: Modifier = Modifier,
    workoutUi: WorkoutUI
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = workoutUi.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = workoutUi.exerciseTitles,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}