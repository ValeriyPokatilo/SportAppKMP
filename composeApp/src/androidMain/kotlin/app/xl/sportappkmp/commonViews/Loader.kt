package app.xl.sportappkmp.commonViews

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.xl.sportappkmp.MR

@Composable
fun Loader(context: Context) {
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