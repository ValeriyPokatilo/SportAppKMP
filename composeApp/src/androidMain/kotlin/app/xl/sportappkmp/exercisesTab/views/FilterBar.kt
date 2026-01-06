package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterBar(
    modifier: Modifier = Modifier,
    showResetButton: Boolean,
    equipmentTitle: String,
    muscleGroupTitle: String,
    unitTypeTitle: String,
    onEquipmentClick: () -> Unit,
    onMuscleClick: () -> Unit,
    onUnitTypeClick: () -> Unit,
    onResetFilterClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterBarButton(
            modifier = Modifier.weight(1f),
            title = equipmentTitle,
            onClick = onEquipmentClick
        )

        FilterBarButton(
            modifier = Modifier.weight(1f),
            title = muscleGroupTitle,
            onClick = onMuscleClick
        )

        FilterBarButton(
            modifier = Modifier.weight(1f),
            title = unitTypeTitle,
            onClick = onUnitTypeClick
        )

        if (showResetButton) {
            Button(
                modifier = Modifier
                    .size(24.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Red),
                contentPadding = PaddingValues(0.dp),
                onClick = onResetFilterClick
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Reset filter"
                )
            }
        }
    }
}