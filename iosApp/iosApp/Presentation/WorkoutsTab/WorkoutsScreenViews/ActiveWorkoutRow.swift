//
//  ActiveWorkoutRow.swift
//  iosApp
//
//  Created by Valeriy P on 23.01.2026.
//

import SwiftUI
import Shared

struct ActiveWorkoutRow: View {
    
    let exerciseTitle: String
    let exerciseIconName: String
    let onAddSetTap: () -> ()
    
    var body: some View {
        HStack(alignment: .center) {
            Image(uiImage: ResourcesKt.getImageByFileName(name: exerciseIconName).toUIImage() ?? UIImage())
                .resizable()
                .scaledToFill()
                .frame(width: 52, height: 52)
            
            Spacer()

            Text(exerciseTitle)
                .font(.system(size: 18, weight: .bold))
                .multilineTextAlignment(.center)
                .foregroundStyle(Color(MR.colors().baseGreen.getUIColor()))
                .lineLimit(2)
            
            Spacer()
            
            Button {
                onAddSetTap()
            } label: {
                Image(systemName: "plus")
                    .font(.system(size: 22, weight: .bold))
                    .foregroundStyle(.white)
                    .frame(width: 32, height: 32)
                    .background(Color(MR.colors().baseGreen.getUIColor()))
                    .clipShape(Circle())
            }
        }
        .padding(8)
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.25), radius: 8, x: 6, y: 6)
        .padding(.horizontal, 20)
        .padding(.vertical, 8)
    }
}
