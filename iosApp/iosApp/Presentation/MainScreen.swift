//
//  MainScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import SwiftUI
import Shared

struct MainScreen: View {

    enum TabItem {
        case workouts, exercises, info
    }
    
    @State private var selectedTab: TabItem = .workouts
        
    var body: some View {
        TabView(selection: $selectedTab) {
            WorkoutsScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().workoutsTabTitle), systemImage: "play.circle")
                }
                .tag(TabItem.workouts)
            ExercisesScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().exercisesTabTitle), systemImage: "list.bullet.circle")
                }
                .tag(TabItem.exercises)
            InfoScreen()
                .tabItem {
                    Label(Localizer().get(id: MR.strings().infoTabTitle), systemImage: "info.circle")
                }
                .tag(TabItem.info)
        }
        .tint(Color(MR.colors().baseGreen.getUIColor()))
    }
}

#Preview {
    MainScreen()
}
