//
//  ExercisesFilterBar.swift
//  iosApp
//
//  Created by Valeriy P on 03.01.2026.
//

import SwiftUI

struct ExercisesFilterBar: View {
    
    let equipmentTitle: String
    let muscleGroupTitle: String
    let unitTypeTitle: String
    let showResetButton: Bool
    let onEquipmentTap: () -> ()
    let onMuscleGroupTap: () -> ()
    let onUnitTypeTap: () -> ()
    let onResetButtonTap: () -> ()
    
    var body: some View {
        HStack(spacing: 8) {
            ExercisesFilterBarButton(title: equipmentTitle) {
                onEquipmentTap()
            }
            
            ExercisesFilterBarButton(title: muscleGroupTitle) {
                onMuscleGroupTap()
            }
            
            ExercisesFilterBarButton(title: unitTypeTitle) {
                onUnitTypeTap()
            }
            
            if showResetButton {
                Button {
                    onResetButtonTap()
                } label: {
                    Image(systemName: "multiply.circle.fill")
                        .resizable()
                        .scaledToFill()
                        .foregroundStyle(.red)
                        .frame(width: 20, height: 20)
                }
            }
        }
    }
} 

#Preview {
    ExercisesScreen()
}
