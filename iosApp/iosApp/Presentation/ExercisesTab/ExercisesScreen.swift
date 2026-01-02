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
    
    private var queryBinding: Binding<String> {
        Binding(
            get: { screenState.query },
            set: { viewModel.onQueryChange(query: $0) }
        )
    }
    
    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                TextField(
                    Localizer().get(id: MR.strings().searchBarPlaceholder, args: []),
                    text: queryBinding
                )
                .padding()
                .overlay {
                    RoundedRectangle(cornerRadius: 12)
                        .stroke(Color.gray, lineWidth: 1)
                }
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
                Localizer().get(id: MR.strings().exercisesTabTitle, args: [])
            )
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

#Preview {
    ExercisesScreen()
}
