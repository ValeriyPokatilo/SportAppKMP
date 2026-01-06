package app.xl.sportappkmp.exercisesTab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.xl.sportappkmp.MR
import app.xl.sportappkmp.exercisesTab.views.ExerciseListRow
import app.xl.sportappkmp.exercisesTab.views.FilterBar
import app.xl.sportappkmp.exercisesTab.views.SearchBar
import app.xl.sportappkmp.exercisesTab.views.EnumPickerSheet
import app.xl.sportappkmp.models.Equipment
import app.xl.sportappkmp.models.FilterSheetType
import app.xl.sportappkmp.models.MuscleGroup
import app.xl.sportappkmp.models.UnitType
import app.xl.sportappkmp.utils.Localizer
import app.xl.sportappkmp.viewModels.exercisesTab.ExercisesScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(
    modifier: Modifier = Modifier,
    viewModel: ExercisesScreenViewModel = viewModel(),
    onExerciseClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val activeSheet by viewModel.activeSheet.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val context = LocalContext.current
    val localizer = Localizer(context)


    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(MR.colors.baseGray.getColor(context)))
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            query = state.query,
            onQueryChange = { query ->
                viewModel.onQueryChange(query = query)
            },
            onResetQuery = {
                viewModel.resetQuery()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        FilterBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            showResetButton = state.equipment != null
                    || state.muscleGroup != null
                    || state.unitType != null,
            equipmentTitle = state.equipment?.localizedTitle(localizer)
                ?: localizer.get(MR.strings.allEquipment),
            muscleGroupTitle = state.muscleGroup?.localizedTitle(localizer)
                ?: localizer.get(MR.strings.allMuscles),
            unitTypeTitle = state.unitType?.localizedTitle(localizer)
                ?: localizer.get(MR.strings.allUnitTypes),
            onEquipmentClick = { viewModel.openSheet(FilterSheetType.EQUIPMENT) },
            onMuscleClick = { viewModel.openSheet(FilterSheetType.MUSCLE) },
            onUnitTypeClick = { viewModel.openSheet(FilterSheetType.UNIT_TYPE)},
            onResetFilterClick = { viewModel.resetFilters() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(
                items = state.exercises,
                key = { _, exercise -> exercise.id }
            ) { index, exercise ->
                ExerciseListRow(
                    localizer = localizer,
                    exercise = exercise,
                    onClick = onExerciseClick
                )
            }
        }
    }

    if (activeSheet != FilterSheetType.NONE) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { viewModel.closeSheet() },
            containerColor = Color.White
        ) {
            when (activeSheet) {
                FilterSheetType.EQUIPMENT -> EnumPickerSheet(
                    localizer = localizer,
                    items = Equipment.allCases,
                    onSelect = {
                        viewModel.onEquipmentChange(it)
                        viewModel.closeSheet()
                    }
                )

                FilterSheetType.MUSCLE -> EnumPickerSheet(
                    localizer = localizer,
                    items = MuscleGroup.allCases,
                    onSelect = {
                        viewModel.onMuscleGroupChange(it)
                        viewModel.closeSheet()
                    }
                )

                FilterSheetType.UNIT_TYPE -> EnumPickerSheet(
                    localizer = localizer,
                    items = UnitType.allCases,
                    onSelect = {
                        viewModel.onUnitTypeChange(it)
                        viewModel.closeSheet()
                    }
                )

                // TODO: - can't use the optional FilterSheetType - crash on iOS
                FilterSheetType.NONE -> Unit
            }
        }
    }
}
