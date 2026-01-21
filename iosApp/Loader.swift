//
//  Loader.swift
//  iosApp
//
//  Created by Valeriy P on 21.01.2026.
//

import SwiftUI
import Shared

struct Loader: View {
    var body: some View {
        VStack {
            Spacer()
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle(tint: Color(MR.colors().baseGreen.getUIColor())))
            Spacer()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(MR.colors().baseGray.getUIColor()))
    }
}

#Preview {
    Loader()
}
