//
//  EditWorkoutScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import Shared
import SwiftUI

struct EditWorkoutScreen: View {

    @Environment(\.dismiss) private var dismiss
    @StateObject var viewModel: EditWorkoutScreenViewModel

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
        ScrollView {
            ZStack {
                AddButton(onTap: {})
                VStack(alignment: .leading) {
                    Text(localizer.get(id: MR.strings().workoutNameStr))
                        .font(.system(size: 12))
                    
                    TextField("", text: titleBinding)
                        .textFieldStyle(.roundedBorder)
                        .background(.white)
                    
                    LazyVStack(alignment: .leading, spacing: 0) {
                        ForEach(screenState.exercises, id: \.id) { exercise in
                            ZStack {
                                ExerciseListRow(exercise: exercise)
                                    .padding(.vertical, 4)
                                    .cornerRadius(8)
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .contentShape(Rectangle())
                                
                                HStack {
                                    Spacer()
                                    Button {
                                        viewModel.onDeleteExercise(
                                            exerciseId: exercise.id
                                        )
                                    } label: {
                                        Image(systemName: "trash")
                                            .font(.system(size: 18, weight: .bold))
                                            .foregroundStyle(.white)
                                            .frame(width: 36, height: 36)
                                            .background(Color.red)
                                            .clipShape(Circle())
                                    }
                                }
                            }
                            Divider()
                        }
                    }
                    Spacer()
                }
            }
            .padding(.horizontal, 20)
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
        }
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}
