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
                NavigationLink(value: HomeDestinations.allItems) {
                    Image(systemName: "cloud.fill").foregroundColor(.blue)
                    Text("home_all_items")
                }
                NavigationLink(value: HomeDestinations.unread) {
                    Image(systemName: "tray.fill").foregroundColor(.green)
                    Text("home_unread")
                }
                NavigationLink(value: HomeDestinations.untagged) {
                    Image(systemName: "xmark.bin.fill").foregroundColor(.gray)
                    Text("home_untagged")
                }
                CollapsibleSection(title: "home_tags") {
                    ForEach(model.state.tags, id: \.name) { tag in
                        NavigationLink(value: HomeDestinations.tag(tag.name)) {
                            HStack {
                                Image(systemName: "number")
                                Text(tag.name)
                                Spacer()
                                Text("\(tag.numberOfBookmarks)")
                            }
                        }.buttonStyle(PlainButtonStyle())
                    }
                }
                NavigationLink(value: HomeDestinations.trash) {
                    Image(systemName: "archivebox.fill").foregroundColor(.gray)
                    Text("home_trash")
                }
            }
            .navigationTitle("home_title")
            .toolbar {
                Menu {
                    Button {
                        
                    } label: {
                        Text("add_link")
                        Image(systemName: "link")
                    }
                    Button {
                        model.sortTags(sorting: TagsSorting.byName)
                    } label: {
                        Text("sort_name")
                        Image(systemName: "a.circle")
                    }
                    Button {
                        model.sortTags(sorting: TagsSorting.byCount)
                    } label: {
                        Text("sort_count")
                        Image(systemName: "number.circle")
                    }
                } label: {
                    Image(systemName: "ellipsis.circle")
                }
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
