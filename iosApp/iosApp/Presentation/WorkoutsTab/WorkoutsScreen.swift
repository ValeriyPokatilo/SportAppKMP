//
//  WorkoutsScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import Shared
import SwiftUI

struct WorkoutsScreen: View {

    @StateObject private var viewModel = WorkoutsScreenViewModel()

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
        NavigationStack {
            VStack(spacing: 0) {
                VStack(spacing: 16) {
                    ForEach(workouts, id: \.id) { workoutUi in
                        WorkoutListRow(workoutUi: workoutUi)
                            .padding(.horizontal, 20)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                }
                
                Spacer()
            }
            .navigationTitle(Localizer().get(id: MR.strings().workoutsTabTitle))
            .navigationBarTitleDisplayMode(.inline)
            .background(Color(MR.colors().baseGray.getUIColor()))
        }
    }
}

#Preview {
    WorkoutsScreen()
}
