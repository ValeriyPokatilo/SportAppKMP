package app.xl.sportappkmp.workoutsTab.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.ExerciseSet
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.formatSetDate
import app.xl.sportappkmp.utils.getImageByFileName
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ActiveWorkoutRow(
    modifier: Modifier = Modifier,
    exerciseTitle: String,
    exerciseIconName: String,
    sets: List<ExerciseSet>,
    history: List<ExerciseSet>,
    onAddSetClick: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(56.dp),
                    painter = painterResource(getImageByFileName(exerciseIconName)),
                    contentDescription = "Exercise icon",
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = exerciseTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(MR.colors.baseGreen.getColor(context)),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Box(
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        modifier = Modifier.size(40.dp),
                        onClick = onAddSetClick,
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(MR.colors.baseGreen.getColor(context))
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add workout",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val maxSize = maxOf(sets.size, history.size)

                for (index in 0 until maxSize) {
                    val currentSet = sets.getOrNull(index)
                    val historySet = history.getOrNull(index)

                    when {
                        currentSet != null -> {
                            ActiveWorkoutSetRow(
                                reps = currentSet.reps.toString(),
                                weight = currentSet.weight.toString(),
                                date = formatSetDate(isoDate = currentSet.timeStamp, isCurrent = true),
                                isCurrent = true
                            )
                        }

                        historySet != null -> {
                            ActiveWorkoutSetRow(
                                reps = historySet.reps.toString(),
                                weight = historySet.weight.toString(),
                                date = formatSetDate(isoDate = historySet.timeStamp, isCurrent = false),
                                isCurrent = false
                            )
                        }
                    }
                }
            }
        }
    }
}