package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    exercise: Exercise,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick(exercise.id)
            },
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
                modifier = Modifier
                    .size(68.dp)
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(getImageByFileName(exercise.iconName)),
                contentDescription = "Exercise icon",
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
                        .equipments
                        .joinToString(", ") { it.localizedTitle(localizer) },
                )

                ExerciseListRowText(
                    text = exercise.unitType.localizedTitle((localizer))
                )
            }
        }
    }
}