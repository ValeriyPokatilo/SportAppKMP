//
//  InfoDetailsBlock.swift
//  iosApp
//
//  Created by Valeriy P on 06.01.2026.
//

import SwiftUI

struct InfoDetailsBlock: View {

    let title: String
    let images: [Image]

    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text(title)
                .font(.system(size: 16, weight: .bold))
            HStack(spacing: 16) {
                ForEach(Array(images.enumerated()), id: \.offset) { index, image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 80, height: 80)
                        .clipShape(RoundedRectangle(cornerRadius: 12))
                        .shadow(color: .black.opacity(0.25), radius: 8, x: 6, y: 6)
                        .overlay {
                            RoundedRectangle(cornerRadius: 12)
                                .stroke(Color.gray, style: StrokeStyle(lineWidth: 1))
                        }
                }
                Spacer()
            }
        }
    }
}

#Preview {
    InfoDetailsBlock(title: "Title", images: [])
}
