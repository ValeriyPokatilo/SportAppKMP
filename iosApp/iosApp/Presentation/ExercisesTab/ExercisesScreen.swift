//
//  ExercisesScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import Shared
import SwiftUI

struct ExercisesScreen: View {

    @ObservedObject var viewModel = ExercisesScreenViewModel()
    
    private let localizer = Localizer()
    
    private var screenState: ExerciseListState {
        viewModel.state(
            \.state,
            equals: { $0 == $1 },
            mapper: { $0 }
        )
    }
    
    private var queryBinding: Binding<String> {
        Binding(
            get: { screenState.query },
            set: { viewModel.onQueryChange(query: $0) }
        )
    }
    
    private var activeSheetBinding: Binding<FilterSheetType?> {
        Binding(
            get: {
                let sheet = viewModel.state(\.activeSheet, equals: { $0 == $1 }, mapper: { $0 })
                return sheet == .none ? nil : sheet
            },
            set: { newValue in
                if let sheet = newValue {
                    viewModel.openSheet(type: sheet)
                } else {
                    viewModel.closeSheet()
                }
            }
        )
    }
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                TextField(
                    localizer.get(id: MR.strings().searchBarPlaceholder),
                    text: queryBinding
                )
                .padding()
                .overlay {
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(Color.gray, lineWidth: 1)
                    
                    if !queryBinding.wrappedValue.isEmpty {
                        HStack {
                            Spacer()
                            Button {
                                queryBinding.wrappedValue = ""
                            } label: {
                                Image(systemName: "xmark.circle.fill")
                                    .foregroundColor(.gray)
                                    .padding(.trailing, 12)
                            }
                            .transition(.opacity.combined(with: .scale))
                        }
                    }
                }
                .padding(.horizontal, 20)
                
                Spacer()
                    .frame(height: 16)
                
                ExercisesFilterBar(
                    equipmentTitle: localizer.getOpt(id: screenState.equipment?.titleRes) ?? localizer.get(id: MR.strings().allEquipment),
                    muscleGroupTitle: localizer.getOpt(id: screenState.muscleGroup?.titleRes) ?? localizer.get(id: MR.strings().allMuscles),
                    unitTypeTitle: localizer.getOpt(id: screenState.unitType?.titleRes) ?? localizer.get(id: MR.strings().allUnitTypes),
                    showResetButton: screenState.equipment != nil || screenState.muscleGroup != nil || screenState.unitType != nil,
                    onEquipmentTap: {
                        viewModel.openSheet(type: .equipment)
                    },
                    onMuscleGroupTap: {
                        viewModel.openSheet(type: .muscle)
                    },
                    onUnitTypeTap: {
                        viewModel.openSheet(type: .unitType)
                    },
                    onResetButtonTap: {
                        viewModel.resetFilters()
                    }
                )
                    .padding(.horizontal, 20)
                
                Spacer()
                    .frame(height: 16)
                
                Divider()
                    .padding(.horizontal, 20)
                
                List(screenState.exercises, id: \.id) { exercise in
                    ExerciseListRow(exercise: exercise)
                        .listRowSeparator(.hidden)
                        .listRowInsets(EdgeInsets(top: 4, leading: 20, bottom: 4, trailing: 20))
                        .id(exercise.id)
                }
                .listStyle(.plain)
                .background(.clear)
                .scrollContentBackground(.hidden)
            }
            .navigationTitle(
                localizer.get(id: MR.strings().exercisesTabTitle)
            )
            .navigationBarTitleDisplayMode(.inline)
            .sheet(item: activeSheetBinding) { sheet in
                switch sheet {
                case .equipment:
                    EnumPickerSheet(
                        items: Equipment.companion.allCases,
                        onSelect: { selected in
                            viewModel.onEquipmentChange(equipment: selected)
                            viewModel.closeSheet()
                        }
                    )
                case .muscle:
                    EnumPickerSheet(
                        items: MuscleGroup.companion.allCases,
                        onSelect: { selected in
                            viewModel.onMuscleGroupChange(muscleGroup: selected)
                            viewModel.closeSheet()
                        }
                    )
                case .unitType:
                    EnumPickerSheet(
                        items: UnitType.companion.allCases,
                        onSelect: { selected in
                            viewModel.onUnitTypeChange(unitType: selected)
                            viewModel.closeSheet()
                        }
                    )
                default:
                    EmptyView()
                }
            }
        }
    }
}

#Preview {
    ExercisesScreen()
}
