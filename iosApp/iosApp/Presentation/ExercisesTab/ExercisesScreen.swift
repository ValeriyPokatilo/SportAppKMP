//
//  ExercisesScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import Shared
import SwiftUI

struct ExercisesScreen: View {

    @StateObject private var viewModel = ExercisesScreenViewModel()
    @State private var path = NavigationPath()

    private let localizer = Localizer()

    private var screenState: ExerciseListState {
        viewModel.state(
            \.state,
            equals: ==,
            mapper: { $0 }
        )
    }

    private var queryBinding: Binding<String> {
        .init(
            get: { screenState.query },
            set: viewModel.onQueryChange
        )
    }

    private var activeSheetBinding: Binding<FilterSheetType?> {
        Binding(
            get: {
                let sheet = viewModel.state(
                    \.activeSheet,
                    equals: { $0 == $1 },
                    mapper: { $0 }
                )
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

    var equipmentTitle: String {
        localizer.getOpt(id: screenState.equipment?.titleRes)
            ?? localizer.get(id: MR.strings().allEquipment)
    }

    var muscleGroupTitle: String {
        localizer.getOpt(id: screenState.muscleGroup?.titleRes)
            ?? Localizer().get(id: MR.strings().allMuscles)
    }

    var unitTypeTitle: String {
        localizer.getOpt(id: screenState.unitType?.titleRes)
            ?? Localizer().get(id: MR.strings().allUnitTypes)
    }

    var showResetButton: Bool {
        screenState.equipment != nil
            || screenState.muscleGroup != nil
            || screenState.unitType != nil
    }

    var body: some View {
        NavigationStack(path: $path) {
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
                .background(.white)

                Spacer()
                    .frame(height: 16)

                ExercisesFilterBar(
                    equipmentTitle: equipmentTitle,
                    muscleGroupTitle: muscleGroupTitle,
                    unitTypeTitle: unitTypeTitle,
                    showResetButton: showResetButton,
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

                Spacer()
                    .frame(height: 16)

                Divider()

                ScrollView {
                    LazyVStack(alignment: .leading, spacing: 0) {
                        ForEach(screenState.exercises, id: \.id) { exercise in
                            NavigationLink(
                                value: ExercisesRoute.detail(exercise.id)
                            ) {
                                ExerciseListRow(exercise: exercise)
                            }
                            .buttonStyle(.plain)
                        }
                    }
                }
            }
            .navigationTitle(
                localizer.get(id: MR.strings().exercisesTabTitle)
            )
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        path.append(ExercisesRoute.create)
                    }) {
                        Image(systemName: "plus")
                    }
                }
            }
            .toolbarBackground(
                Color(MR.colors().baseGray.getUIColor()),
                for: .navigationBar
            )
            .toolbarBackground(.visible, for: .navigationBar)
            .navigationDestination(for: ExercisesRoute.self) { route in
                destination(for: route)
            }
            .sheet(item: activeSheetBinding) { sheet in
                getSheet(for: sheet)
            }
            .padding(.horizontal, 20)
            .background(Color(MR.colors().baseGray.getUIColor()))
        }
    }
    
    @ViewBuilder
    private func destination(for route: ExercisesRoute) -> some View {
        switch route {
        case .detail(let id):
            ExercisesInfoScreen(path: $path, exerciseId: id)
        case .edit(let id):
            ExerciseCreateScreen(id: id)
        case .create:
            ExerciseCreateScreen(id: nil)
        }
    }

    @ViewBuilder
    private func getSheet(for sheet: FilterSheetType) -> some View {
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

#Preview {
    ExercisesScreen()
}
