//
//  ActiveWorkoutSetRow.swift
//  iosApp
//
//  Created by Valeriy P on 30.01.2026.
//

import SwiftUI
import Shared

struct ActiveWorkoutSetRow: View {

    let reps: String
    let weight: String?
    let date: String

    var body: some View {
        ZStack {
            if let weight {
                Text(weight)
                    .font(.system(size: 12, weight: .bold))
            }
            
            HStack {
                Text(reps)
                    .font(.system(size: 12, weight: .bold))
                
                Spacer()
                
                Text(date)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(Color(MR.colors().baseBlue.getUIColor()))
            }
        }
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .frame(maxWidth: .infinity)
        .clipShape(RoundedRectangle(cornerRadius: 8))
        .background(Color(MR.colors().baseGreen.getUIColor()).opacity(0.1))
    }
}
