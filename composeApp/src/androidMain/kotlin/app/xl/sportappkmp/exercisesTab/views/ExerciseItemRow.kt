package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.utils.getImageByFileName
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ExerciseListRow(
    modifier: Modifier = Modifier,
    localizer: Localizer,
    exercise: Exercise
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = exercise.localizedTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(getImageByFileName(exercise.iconName)),
                contentDescription = "contentDescription",
                modifier = Modifier
                    .size(68.dp)
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(12.dp)
                    )
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                ExerciseListRowText(
                    text = exercise
                        .muscleGroups
                        .joinToString(", ") { it.localizedTitle(localizer) }
                )

                ExerciseListRowText(
                    text = exercise
                        .equipment
                        .joinToString(", ") { it.localizedTitle(localizer) },
                )

                ExerciseListRowText(
                    text = exercise.unitType.localizedTitle((localizer))
                )
            }
        }
    }
}