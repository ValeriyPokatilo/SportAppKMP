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

    private var sets: [ExerciseSet] {
        viewModel.state(
            \.sets,
            equals: { oldValue, newValue in
                guard let oldArr = oldValue as? [ExerciseSet],
                    let newArr = newValue as? [ExerciseSet]
                else { return false }
                return oldArr == newArr
            },
            mapper: { value in
                value as? [ExerciseSet] ?? []
            }
        )
    }

    private var history: [ExerciseSet] {
        viewModel.state(
            \.history,
            equals: { oldValue, newValue in
                guard let oldArr = oldValue as? [ExerciseSet],
                    let newArr = newValue as? [ExerciseSet]
                else { return false }
                return oldArr == newArr
            },
            mapper: { value in
                value as? [ExerciseSet] ?? []
            }
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
                fileManager: Shared.FileManager(),
                databaseDriverFactory: DatabaseDriverFactory()
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
                        #if DEBUG
                        Button {
                            viewModel.shiftAllSetsOneDayBack()
                        } label: {
                            Text("DEBUG: shift all sets one day back")
                        }
                        .buttonStyle(.plain)
                        .foregroundStyle(.white)
                        .frame(maxWidth: .infinity)
                        .frame(height: 32)
                        .background(.red)
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                        .padding(.horizontal, 20)
                        #endif
                        
                        ForEach(state.exercises, id: \.id) { exercise in
                            ActiveWorkoutRow(
                                exerciseTitle: exercise.localizedTitle,
                                exerciseIconName: exercise.iconName,
                                sets: sets.filter({ $0.exerciseId == exercise.id }),
                                history: history.filter({ $0.exerciseId == exercise.id }),
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
                onSaveTap: { reps, weight in
                    let isoFormatter = ISO8601DateFormatter()
                    isoFormatter.formatOptions = [
                        .withInternetDateTime,
                        .withFractionalSeconds,
                    ]
                    let isoDate = isoFormatter.string(from: Date())

                    viewModel.onAddSetClick(
                        exerciseId: exercise.id,
                        reps: Int64(reps),
                        weight: weight,
                        timeStamp: isoDate
                    )
                }
            )
            .presentationBackground(.black.opacity(0.4))
        }
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}
