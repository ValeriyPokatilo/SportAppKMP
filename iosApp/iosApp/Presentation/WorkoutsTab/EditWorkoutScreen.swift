//
//  EditWorkoutScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import Shared
import SwiftUI

struct EditWorkoutScreen: View {

    @StateObject private var viewModel: EditWorkoutScreenViewModel
    @Environment(\.dismiss) private var dismiss

    private var screenState: WorkoutInfoState {
        viewModel.state(
            \.state,
            equals: { _, _ in false },
            mapper: { $0 }
        )
    }

    private var titleBinding: Binding<String> {
        Binding(
            get: { screenState.title },
            set: { viewModel.onTitleChanged(title: $0) }
        )
    }

    private let localizer = Localizer()

    init(id: String?) {
        _viewModel = StateObject(
            wrappedValue: EditWorkoutScreenViewModel(
                workoutId: id,
                fileManager: Shared.FileManager()
            )
        )
    }

    var body: some View {
        ZStack {
            AddButton(onTap: {
                // TODO: - show exercises list for add exercise
            })
            VStack(alignment: .leading) {
                Text(localizer.get(id: MR.strings().workoutNameStr))
                    .font(.system(size: 12))

                TextField("", text: titleBinding)
                    .textFieldStyle(.roundedBorder)
                    .background(.white)

                ScrollView {
                    LazyVStack(alignment: .leading, spacing: 0) {
                        ForEach(screenState.exercises, id: \.id) { exercise in
                            // TODO: - add swipe to delete action
                            ExerciseListRow(exercise: exercise)
                        }
                    }
                }

                Spacer()
            }
        }
        .navigationTitle(titleBinding)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    viewModel.onSaveClicked()
                    dismiss()
                } label: {
                    Text("💾")
                }
            }
        }
        .padding(.horizontal, 20)
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}
