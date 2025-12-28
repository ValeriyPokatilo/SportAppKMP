//
//  TabBarScreen.swift
//  iosApp
//
//  Created by Valeriy P on 28.12.2025.
//

import SwiftUI

struct TabBarScreen: View {
    
    enum TabItem {
        case workouts, exercises, info
    }
    
    @State private var selectedTab: TabItem = .exercises
    
    var body: some View {
        TabView(selection: $selectedTab) {
            WorkoutsScreen()
                .tabItem {
                    Label("Тренировки", systemImage: "house")
                }
                .tag(TabItem.workouts)
            ExercisesScreen()
                .tabItem {
                    Label("Упражнения", systemImage: "house")
                }
                .tag(TabItem.exercises)
            InfoScreen()
                .tabItem {
                    Label("Инфо", systemImage: "house")
                }
                .tag(TabItem.info)
        }
    }
}

#Preview {
    TabBarScreen()
}
