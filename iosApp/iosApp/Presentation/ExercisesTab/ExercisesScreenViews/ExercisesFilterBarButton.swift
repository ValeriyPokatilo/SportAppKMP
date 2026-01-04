//
//  ExercisesFilterBarButton.swift
//  iosApp
//
//  Created by Valeriy P on 03.01.2026.
//

import SwiftUI
import Shared

struct ExercisesFilterBarButton: View {
    
    let title: String
    let onButtonTap: () -> ()
    
    var body: some View {
        Button {
            onButtonTap()
        } label: {
            Text(title)
                .font(.system(size: 14))
                .lineLimit(1)
                .truncationMode(.tail)
                .frame(maxWidth: .infinity)
                .padding(.vertical, 4)
                .background(Color(MR.colors().baseGreen.getUIColor()))
                .foregroundStyle(.white)
                .cornerRadius(8)
        }
        .buttonStyle(.plain)
    }
}

#Preview {
    ExercisesScreen()
}
