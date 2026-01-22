//
//  ExerciseCreateScreen.swift
//  iosApp
//
//  Created by Valeriy P on 22.01.2026.
//

import SwiftUI

struct ExerciseCreateScreen: View {
    
    private let id: String?
    
    init(id: String?) {
        self.id = id
    }
    
    var body: some View {
        if let id {
            Text("Edit \(id)")
        } else {
            Text("Create new exercise")
        }
    }
}
