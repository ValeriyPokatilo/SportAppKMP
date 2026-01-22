//
//  ExercisesInfoScreen.swift
//  iosApp
//
//  Created by Valeriy P on 05.01.2026.
//

import SwiftUI
import Shared

struct ExercisesInfoScreen: View {
    
    @StateObject var viewModel: ExerciseInfoScreenViewModel
    @Binding var path: NavigationPath
    private let exerciseId: String

    init(path: Binding<NavigationPath>, exerciseId: String) {
        self.exerciseId = exerciseId
        self._path = path
        _viewModel = StateObject(
            wrappedValue: ExerciseInfoScreenViewModel(exerciseId: exerciseId)
        )
    }
    
    private let localizer = Localizer()
    
    private var screenState: InfoScreenState {
        viewModel.state(
            \.state,
            equals: { _, _ in false },
            mapper: { $0 }
        )
    }
    
    var body: some View {
        switch screenState {
        case is InfoScreenStateInitial:
            Loader()
        case let state as InfoScreenStateInfo:
            VStack {
                VStack(spacing: 24) {
                    // TODO: - add segmented control with history
                    
                    Image(uiImage: ResourcesKt.getImageByFileName(name: state.exercise.imageName).toUIImage() ?? UIImage())
                        .resizable()
                        .scaledToFit()
                        .clipShape(RoundedRectangle(cornerRadius: 12))
                        .shadow(color: .black.opacity(0.25), radius: 8, x: 6, y: 6)
                    
                    InfoDetailsBlock(
                        title: localizer.get(id: MR.strings().equipments),
                        images: state.exercise.equipments.map {
                            Image(uiImage: $0.imageRes.toUIImage() ?? UIImage())
                        }
                    )
                    
                    InfoDetailsBlock(
                        title: localizer.get(id: MR.strings().muscles),
                        images: state.exercise.muscleGroups.map {
                            Image(uiImage: $0.imageRes.toUIImage() ?? UIImage())
                        }
                    )
                }
                .padding(.horizontal, 20)
                
                Spacer()
                
                // TODO: - add Report mistake
            }
            .background(Color(MR.colors().baseGray.getUIColor()))
            .navigationTitle(state.exercise.localizedTitle)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        path.append(ExercisesRoute.edit(exerciseId))
                    }) {
                        Image(systemName: "pencil.circle")
                    }
                }
            }
        case is InfoScreenStateGoBack:
            EmptyView()
        default:
            EmptyView()
        }
    }
}
