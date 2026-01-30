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
    let sets: [ExerciseSet]
    let history: [ExerciseSet]
    let onAddSetTap: () -> ()
    
    var body: some View {
        VStack {
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
                .padding(.trailing, 8)
            }
            
            VStack(spacing: 4) {
                let maxSize = max(sets.count, history.count)

                ForEach(0..<maxSize, id: \.self) { index in
                    let currentSet = index < sets.count ? sets[index] : nil
                    let historySet = index < history.count ? history[index] : nil

                    if let currentSet {
                        ActiveWorkoutSetRow(
                            reps: String(currentSet.reps),
                            weight: String(currentSet.weight),
                            date: DateFormatterKt.formatSetDate(
                                isoDate: currentSet.timeStamp,
                                isCurrent: true
                            )
                        )
                    } else if let historySet {
                        ActiveWorkoutHistoryRow(
                            reps: String(historySet.reps),
                            weight: String(historySet.weight),
                            date: DateFormatterKt.formatSetDate(
                                isoDate: historySet.timeStamp,
                                isCurrent: false
                            )
                        )
                    }
                }
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
