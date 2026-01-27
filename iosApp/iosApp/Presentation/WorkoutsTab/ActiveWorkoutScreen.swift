//
//  ActiveWorkoutScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import Shared
import SwiftUI

struct ActiveWorkoutScreen: View {

    @State private var selectedExercise: Exercise? = nil
    @StateObject private var viewModel: ActiveWorkoutScreenViewModel
    @Binding var path: NavigationPath

    private let id: String

    private var screenState: ActiveWorkoutState {
        viewModel.state(
            \.state,
            equals: { _, _ in false },
            mapper: { $0 }
        )
    }

    private var navigationTitle: String? {
        guard let state = screenState as? ActiveWorkoutStateReady else {
            return nil
        }
        return state.workout.title
    }

    init(path: Binding<NavigationPath>, id: String) {
        self.id = id
        self._path = path
        _viewModel = StateObject(
            wrappedValue: ActiveWorkoutScreenViewModel(
                workoutId: id,
                fileManager: Shared.FileManager()
            )
        )
    }

    var body: some View {
        ScrollView {
            VStack {
                switch screenState {
                case is ActiveWorkoutStateInitial:
                    Loader()

                case let state as ActiveWorkoutStateReady:
                    LazyVStack(alignment: .leading, spacing: 0) {
                        ForEach(state.exercises, id: \.id) { exercise in
                            ActiveWorkoutRow(
                                exerciseTitle: exercise.localizedTitle,
                                exerciseIconName: exercise.iconName,
                                onAddSetTap: {
                                    selectedExercise = exercise
                                }
                            )
                        }
                    }
                    Spacer()

                default:
                    EmptyView()
                }
            }
        }
        .navigationTitle(navigationTitle ?? "")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: {
                    path.append(WorkoutsRoute.edit(id))
                }) {
                    Image(systemName: "pencil.circle")
                }
            }
        }
        .fullScreenCover(item: $selectedExercise) { exercise in
            AddSetPicker(
                exerciseId: exercise.localizedTitle,
                onSaveTap: {
                    // TODO: -
                }
            )
            .presentationBackground(.black.opacity(0.4))
        }
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}
