//
//  CollapsibleSection.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 13/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct CollapsibleSection<Content: View>: View {

    private let title: String
    private let content: Content
    private let alignment: HorizontalAlignment
    private let spacing: CGFloat
    @State private var isExpanded = true

    init(title: String, alignment: HorizontalAlignment = .leading, spacing: CGFloat = 16, @ViewBuilder content: () -> Content) {
        self.title = title
        self.alignment = alignment
        self.spacing = spacing
        self.content = content()
    }

    var body: some View {
        Section {
            if isExpanded {
                content
            }
        } header: {
            HStack {
                Text("Tags").font(.title3).bold().foregroundColor(.primary).autocapitalization(.none)
                Spacer().frame(width: 16)
                Image(systemName: "chevron.right")
                    .resizable()
                    .scaledToFill()
                    .frame(width: 8.0, height: 8.0, alignment: .center)
                    .rotationEffect(.degrees(isExpanded ? 90 : 0))
            }
            .onTapGesture {
                withAnimation { isExpanded.toggle() }
            }
        }
    }
}
