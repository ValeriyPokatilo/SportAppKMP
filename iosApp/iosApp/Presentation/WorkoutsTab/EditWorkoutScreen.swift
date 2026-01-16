//
//  EditWorkoutScreen.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import Shared
import SwiftUI

struct EditWorkoutScreen: View {

    @StateObject var viewModel: WorkoutInfoScreenViewModel
    @State private var title = ""

    private let localizer = Localizer()

    init(id: String?) {
        _viewModel = StateObject(
            wrappedValue: WorkoutInfoScreenViewModel(
                workoutId: id,
                fileManager: Shared.FileManager()
            )
        )
    }

    private var screenState: WorkoutInfoState {
        viewModel.state(
            \.state,
            equals: { _, _ in false },
            mapper: { $0 }
        )
    }

    var body: some View {
        NavigationStack {
            ZStack {
                AddButton(onTap: {})
                VStack(alignment: .leading) {
                    Text(localizer.get(id: MR.strings().workoutNameStr))
                        .font(.system(size: 12))
                    
                    TextField("", text: $title)
                        .textFieldStyle(.roundedBorder)
                        .background(.white)
                    
                    Spacer()
                }
                .padding(.horizontal, 20)
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button {
                            // TODO: - save
                        } label: {
                            Text("💾")
                        }
                    }
                }
            }
        }
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}
