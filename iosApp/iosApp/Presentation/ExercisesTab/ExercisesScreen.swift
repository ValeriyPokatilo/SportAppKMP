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
    private var screenState: ExerciseListState {
        viewModel.state(
            \.state,
            equals: { $0 == $1 },
            mapper: { $0 }
        )
    }

    var body: some View {
        NavigationStack {
            VStack {
                TextField(
                    Localizer().get(id: MR.strings().searchBarPlaceholder, args: []),
                    text: Binding(
                        get: { screenState.query },
                        set: { viewModel.onQueryChange(query: $0) }
                    )
                )
                .padding()
                .overlay {
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(Color.gray, lineWidth: 1)
                }
                .padding()
                
                List(screenState.exercises, id: \.id) { exercise in
                    Text(exercise.titleRu) // TODO: - use localized title
                }
            }
            .navigationTitle(
                Localizer().get(id: MR.strings().exercisesTabTitle, args: [])
            )
        }
    }
}

#Preview {
    ExercisesScreen()
}
