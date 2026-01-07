//
//  WorkoutListRow.swift
//  iosApp
//
//  Created by Valeriy P on 07.01.2026.
//

import SwiftUI
import Shared

struct WorkoutListRow: View {
    
    let workoutUi: WorkoutUI
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(workoutUi.title)
                .font(.system(size: 20, weight: .bold))
                .foregroundStyle(.black)
            Text(workoutUi.exerciseTitles)
                .font(.system(size: 14))
                .foregroundStyle(.gray)
        }
    }
}

#Preview {
    WorkoutListRow(workoutUi: WorkoutUI(id: "id", title: "title", exercises: []))
}
