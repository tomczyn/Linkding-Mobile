//
//  HomeScreen.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 26/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

enum HomeDestinations : Hashable {
    case allItems
    case unread
    case untagged
    case trash
    case tag(String)
}

struct HomeScreen: View {
    
    var onLogout: () -> Void
    @State private var isLoading: Bool = true
    @State private var path: [HomeDestinations] = []
    @State private var tags: [String] = ["Tag1", "Tag2", "Tag3"]
    @State private var isTagsSectionCollapsed: Bool = false
    @State private var search: String = ""
    
    var body: some View {
        NavigationStack(path: $path) {
            List {
                NavigationLink("All items", value: HomeDestinations.allItems)
                NavigationLink("Unread", value: HomeDestinations.unread)
                NavigationLink("Untagged", value: HomeDestinations.untagged)
                CollapsibleSection(title: "Tags") {
                    ForEach(tags, id: \.self) { tag in
                        NavigationLink(tag, value: HomeDestinations.tag(tag))
                    }
                }
                NavigationLink("Trash", value: HomeDestinations.trash)
            }
            .navigationDestination(for: HomeDestinations.self) { destination in
                switch destination {
                case .allItems:
                    EmptyView()
                case .unread:
                    EmptyView()
                case .untagged:
                    EmptyView()
                case .trash:
                    EmptyView()
                case .tag(_):
                    EmptyView()
                }
            }
        }
        .searchable(text: $search)
    }
}

struct Previews_HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen { }
    }
}
