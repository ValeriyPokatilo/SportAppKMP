package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.xl.sportappkmp.interfaces.LocalizedEnum
import app.xl.sportappkmp.utils.Localizer

@Composable
fun <T> EnumPickerSheet(
    localizer: Localizer,
    items: List<T>,
    onSelect: (T) -> Unit
) where T: Enum<T>, T: LocalizedEnum {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items.forEach { equipment ->
            ListItem(
                headlineContent = { Text(equipment.localizedTitle(localizer)) },
                modifier = Modifier.clickable {
                    onSelect(equipment)
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.White
                )
            )
        }
    }
}

