//
//  WorkoutsScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import Shared
import SwiftUI

struct WorkoutsScreen: View {

    @StateObject private var viewModel = WorkoutsScreenViewModel(
        fileManager: Shared.FileManager()
    )

    @State private var path = NavigationPath()

    private var workouts: [WorkoutUI] {
        viewModel.state(
            \.workouts,
            equals: { oldValue, newValue in
                guard let oldArr = oldValue as? [WorkoutUI],
                    let newArr = newValue as? [WorkoutUI]
                else { return false }
                return oldArr == newArr
            },
            mapper: { value in
                value as? [WorkoutUI] ?? []
            }
        )
    }

    var body: some View {
        NavigationStack(path: $path) {
            ScrollView {
                LazyVStack(spacing: 16) {
                    ForEach(workouts, id: \.id) { workoutUi in
                        NavigationLink(
                            value: WorkoutsRoute.detail(workoutUi.id)
                        ) {
                            WorkoutListRow(workoutUi: workoutUi)
                                .padding(.horizontal, 20)
                                .frame(
                                    maxWidth: .infinity,
                                    alignment: .leading
                                )
                        }
                        .buttonStyle(.plain)
                    }
                }
            }
            .navigationTitle(Localizer().get(id: MR.strings().workoutsTabTitle))
            
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        path.append(WorkoutsRoute.create)
                    }) {
                        Image(systemName: "plus")
                    }
                }
            }
            .navigationDestination(for: WorkoutsRoute.self) { route in
                destination(for: route)
            }
            .background(Color(MR.colors().baseGray.getUIColor()))
        }
    }
    
    @ViewBuilder
    private func destination(for route: WorkoutsRoute) -> some View {
        switch route {
        case .detail(let id):
            ActiveWorkoutScreen(path: $path, id: id)
        case .edit(let id):
            EditWorkoutScreen(id: id)
        case .create:
            EditWorkoutScreen(id: nil)
        }
    }
}

#Preview {
    WorkoutsScreen()
}
