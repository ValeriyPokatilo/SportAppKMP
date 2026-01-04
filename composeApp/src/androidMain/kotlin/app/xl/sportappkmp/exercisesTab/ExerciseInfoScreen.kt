package app.xl.sportappkmp.exercisesTab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.exercisesTab.views.DetailsBlock
import app.xl.sportappkmp.models.Exercise
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.utils.getImageByFileName
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ExerciseInfoScreen(
    modifier: Modifier = Modifier,
    exercise: Exercise
) {
    val context = LocalContext.current
    val localizer = Localizer(context)

    // TODO: - add segmented control with history

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(MR.colors.baseGray.getColor(context)))
            .padding(horizontal = 24.dp),
    ) {

        // TODO: - set padding / remove spacer
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = exercise.localizedTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(getImageByFileName(exercise.imageName)),
            contentDescription = "Exercise image",
        )

        Spacer(modifier = Modifier.height(24.dp))

        DetailsBlock(
            localizer.get(MR.strings.equipments),
            exercise.equipments.map { it.imageRes }
        )

        Spacer(modifier = Modifier.height(24.dp))

        DetailsBlock(
            localizer.get(MR.strings.muscles),
            exercise.muscleGroups.map { it.imageRes }
        )

        // TODO: - add Report mistake
    }
}
