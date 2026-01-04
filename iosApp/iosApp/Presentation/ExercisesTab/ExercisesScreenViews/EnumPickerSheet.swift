//
//  EnumPickerSheet.swift
//  iosApp
//
//  Created by Valeriy P on 03.01.2026.
//

import SwiftUI
import Shared

struct EnumPickerSheet<T: LocalizedEnum & Hashable>: View {
    let items: [T]
    let onSelect: (T) -> Void
    private let localizer = Localizer()
    
    @State private var contentHeight: CGFloat = .leastNormalMagnitude

    var body: some View {
        VStack(spacing: 0) {
            ForEach(items, id: \.self) { item in
                Button {
                    onSelect(item)
                } label: {
                    HStack {
                        Text(item.localizedTitle(localizer: localizer))
                            .foregroundColor(.primary)
                        Spacer()
                    }
                    .padding(.horizontal, 20)
                    .padding(.vertical, 14)
                    .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
                
                if item != items.last {
                    Divider()
                        .padding(.leading, 20)
                }
            }
        }
        .padding(.vertical, 20)
        .background(
            GeometryReader { geo in
                Color.clear.onAppear {
                    self.contentHeight = geo.size.height
                }
            }
        )
        .presentationDragIndicator(.visible)
        .presentationDetents([.height(contentHeight)])
        .presentationBackground(Color(uiColor: .systemBackground))
    }
}
