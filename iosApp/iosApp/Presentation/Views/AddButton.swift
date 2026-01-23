//
//  AddButton.swift
//  iosApp
//
//  Created by Valeriy P on 15.01.2026.
//

import SwiftUI
import Shared

struct AddButton: View {

    let onTap: () -> ()

    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                Button {
                    onTap()
                } label: {
                    Image(systemName: "plus")
                        .font(.system(size: 28, weight: .bold))
                        .foregroundStyle(.white)
                        .frame(width: 56, height: 56)
                        .background(Color(MR.colors().baseGreen.getUIColor()))
                        .clipShape(Circle())
                }
                .padding(.trailing, 8)
                .padding(.bottom, 32)
            }
        }

    }
}

#Preview {
    AddButton(onTap: {})
}
