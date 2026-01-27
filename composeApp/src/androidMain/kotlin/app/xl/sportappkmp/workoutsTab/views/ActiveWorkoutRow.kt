package app.xl.sportappkmp.workoutsTab.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.getImageByFileName
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ActiveWorkoutRow(
    modifier: Modifier = Modifier,
    exerciseTitle: String,
    exerciseIconName: String,
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
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
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
    }
}