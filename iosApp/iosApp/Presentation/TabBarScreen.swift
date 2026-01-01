//
//  TabBarScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import SwiftUI
import Shared

struct TabBarScreen: View {

    enum TabItem {
        case workouts, exercises, info
    }
    
    @State private var selectedTab: TabItem = .exercises
        
    var body: some View {
        TabView(selection: $selectedTab) {
            WorkoutsScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().workoutsTabTitle, args: []), systemImage: "house")
                }
                .tag(TabItem.workouts)
            ExercisesScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().exercisesTabTitle, args: []), systemImage: "house")
                }
                .tag(TabItem.exercises)
            InfoScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().infoTabTitle, args: []), systemImage: "house")
                }
                .tag(TabItem.info)
        }
    }
}

#Preview {
    TabBarScreen()
}
