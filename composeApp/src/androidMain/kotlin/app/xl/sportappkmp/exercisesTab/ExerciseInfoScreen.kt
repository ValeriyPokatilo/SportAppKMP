package app.xl.sportappkmp.exercisesTab

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.exercisesTab.views.InfoDetailsBlock
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.utils.getImageByFileName
import app.xl.sportappkmp.viewModels.exercisesTab.ExerciseInfoScreenViewModel
import app.xl.sportappkmp.viewModels.exercisesTab.InfoScreenState
import dev.icerock.moko.resources.compose.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseInfoScreen(
    modifier: Modifier = Modifier,
    exerciseId: String,
    viewModel: ExerciseInfoScreenViewModel = viewModel {
        ExerciseInfoScreenViewModel(exerciseId)
    },
    onFinished: () -> Unit
) {

    val context = LocalContext.current
    val localizer = Localizer(context)

    val currentState by viewModel.state.collectAsStateWithLifecycle()

    when (currentState) {
        is InfoScreenState.Info -> {
            val exercise = (currentState as InfoScreenState.Info).exercise

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = exercise.localizedTitle,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(MR.colors.baseGray.getColor(context)),
                            navigationIconContentColor = Color.DarkGray,
                        ),
                        navigationIcon = {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 8.dp)
                                    .clickable {
                                        viewModel.onFinished()
                                    },
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back button"
                            )
                        },
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color(MR.colors.baseGray.getColor(context)))
                        .padding(horizontal = 24.dp),
                ) {
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

                    InfoDetailsBlock(
                        localizer.get(MR.strings.equipments),
                        exercise.equipments.map { it.imageRes }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    InfoDetailsBlock(
                        localizer.get(MR.strings.muscles),
                        exercise.muscleGroups.map { it.imageRes }
                    )

                    // TODO: - add Report mistake
                }
            }
        }

        InfoScreenState.GoBack -> {
            LaunchedEffect(key1 = Unit) {
                onFinished()
            }
        }

        InfoScreenState.Initial -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(MR.colors.baseGray.getColor(context))),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color(MR.colors.baseGreen.getColor(context))
                )
            }
        }
    }

    // TODO: - add segmented control with history
}
