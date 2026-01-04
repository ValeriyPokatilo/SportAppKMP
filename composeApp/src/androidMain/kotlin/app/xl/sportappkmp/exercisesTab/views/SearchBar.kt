package app.xl.sportappkmp.exercisesTab.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.utils.localizer

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onResetQuery: () -> Unit
) {
    val searchPlaceholder = localizer(MR.strings.searchBarPlaceholder)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White
        ),
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = searchPlaceholder,
                fontSize = 14.sp,
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search notes",
                tint = Color.DarkGray
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = onResetQuery
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear text",
                        tint = Color.DarkGray
                    )
                }
            }
        },
        shape = RoundedCornerShape(10.dp)
    )
}