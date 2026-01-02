//
//  ExerciseListRow.swift
//  iosApp
//
//  Created by Valeriy P on 02.01.2026.
//

import Shared
import SwiftUI

struct ExerciseListRow: View {

    private let localizer = Localizer()

    let exercise: Exercise

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(exercise.localizedTitle)
                .font(.system(size: 18, weight: .bold))
            
            HStack(alignment: .top, spacing: 8) {
                Image(uiImage: ResourcesKt.getImageByFileName(name: exercise.iconName).toUIImage() ?? UIImage())
                    .resizable()
                    .scaledToFill()
                    .frame(width: 52, height: 52)
                    .clipShape(RoundedRectangle(cornerRadius: 12))
                    .overlay {
                        RoundedRectangle(cornerRadius: 12)
                            .stroke(Color.gray, style: StrokeStyle(lineWidth: 1))
                    }
                
                VStack(alignment: .leading, spacing: 4) {
                    Text(
                        exercise.muscleGroups
                            .map { localizer.get(id: $0.titleRes, args: []) }
                            .joined(separator: ", ")
                    )
                    .font(.system(size: 12))
                    
                    Text(
                        exercise.equipment
                            .map { localizer.get(id: $0.titleRes, args: []) }
                            .joined(separator: ", ")
                    )
                    .font(.system(size: 12))
                    
                    Text(exercise.unitType.localizedTitle(localizer: localizer))
                        .font(.system(size: 12))
                }
            }
        }
    }
}
