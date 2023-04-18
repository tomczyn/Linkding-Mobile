//
//  HomeScreen.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 26/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import KMMViewModelSwiftUI
import KMPNativeCoroutinesCombine
import shared

enum HomeDestinations : Hashable {
    case allItems
    case unread
    case untagged
    case trash
    case tag(String)
}

struct HomeScreen: View {
    
    var onLogout: () -> Void
    @StateViewModel private var model = HomeViewModel()
    @State private var isLoading: Bool = true
    @State private var path: [HomeDestinations] = []
    @State private var isTagsSectionCollapsed: Bool = false
    @State private var search: String = ""
    
    var body: some View {
        NavigationStack(path: $path) {
            List {
                NavigationLink("home_all_items", value: HomeDestinations.allItems)
                NavigationLink("home_unread", value: HomeDestinations.unread)
                NavigationLink("home_untagged", value: HomeDestinations.untagged)
                CollapsibleSection(title: "home_tags") {
                    ForEach(model.state.tags, id: \.name) { tag in
                        NavigationLink(tag.name, value: HomeDestinations.tag(tag.name))
                    }
                }
                NavigationLink("home_trash", value: HomeDestinations.trash)
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
        .searchable(text: $search) {
            EmptyView()
        }
    }
}

struct Previews_HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen { }
    }
}
