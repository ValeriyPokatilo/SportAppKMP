//
//  AddSetPicker.swift
//  iosApp
//
//  Created by Valeriy P on 26.01.2026.
//

import SwiftUI
import Shared

struct AddSetPicker: View {
    
    @State private var weight = ""
    @State private var reps = ""
    
    @Environment(\.dismiss) private var dismiss
    
    private let localizer = Localizer()
    
    let exerciseId: String
    let onSaveTap: (Int, Double) -> ()
    
    var body: some View {
        VStack{
            Spacer()
            
            VStack(spacing: 16) {
                Text(exerciseId)
                    .font(.system(size: 24, weight: .bold))
                
                TextField("\(localizer.get(id: MR.strings().reps)) (required)", text: $reps)
                    .textFieldStyle(.roundedBorder)
                    .keyboardType(.numberPad)
                
                TextField("\(localizer.get(id: MR.strings().weight)) (optional)", text: $weight)
                    .textFieldStyle(.roundedBorder)
                    .keyboardType(.decimalPad)
                
                HStack(spacing: 16) {
                    Button {
                        dismiss()
                    } label: {
                        Text(localizer.get(id: MR.strings().cancel))
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.white)
                    .frame(maxWidth: .infinity)
                    .frame(height: 32)
                    .background(.red)
                    .clipShape(RoundedRectangle(cornerRadius: 8))

                    Button {
                        let repsValue = Int(reps)
                        let weightValue = Double(weight) ?? 0.0
                        
                        if let repsValue {
                            onSaveTap(repsValue, weightValue)
                        }
                        
                        dismiss()
                    } label: {
                        Text(localizer.get(id: MR.strings().save))
                    }
                    .buttonStyle(.plain)
                    .foregroundStyle(.white)
                    .frame(maxWidth: .infinity)
                    .frame(height: 32)
                    .background(.green)
                    .clipShape(RoundedRectangle(cornerRadius: 8))
                }
                
            }
            .padding(.horizontal, 20)
            .padding(.vertical, 16)
            .frame(maxWidth: .infinity)
            .background(Color.white)
            .clipShape(RoundedRectangle(cornerRadius: 12))
            .padding(.horizontal, 20)
            
            Spacer()
        }
    }
}
