//
//  ActiveWorkoutScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import Shared
import SwiftUI

struct ActiveWorkoutScreen: View {

    @StateObject var viewModel: ActiveWorkoutScreenViewModel
    @Binding var path: NavigationPath

    private let id: String

    private var screenState: ActiveWorkoutState {
        viewModel.state(
            \.state,
            equals: { _, _ in false },
            mapper: { $0 }
        )
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
                            ExerciseListRow(exercise: exercise)
                                .padding(.vertical, 4)
                                .cornerRadius(8)
                                .frame(
                                    maxWidth: .infinity,
                                    alignment: .leading
                                )
                                .contentShape(Rectangle())
                            Divider()
                        }
                    }
                    Spacer()
                    
                default:
                    EmptyView()
                }
            }
        }
        .padding(.horizontal, 20)
        .background(Color(MR.colors().baseGray.getUIColor()))
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: {
                    path.append(WorkoutsRoute.edit(id))
                }) {
                    Image(systemName: "pencil.circle")
                }
            }
        }
    }
}
