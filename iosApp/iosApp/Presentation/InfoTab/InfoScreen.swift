//
//  InfoScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import SwiftUI
import Shared

struct InfoScreen: View {
    var body: some View {
        NavigationStack {
            VStack {

            }
            .navigationTitle(Localizer().get(id: MR.strings().infoTabTitle, args: []))
        }
    }
}

#Preview {
    InfoScreen()
}
