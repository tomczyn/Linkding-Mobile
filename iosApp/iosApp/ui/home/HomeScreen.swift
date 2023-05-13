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
    case search(String)
    case tag(String)
    case bookmark(Bookmark)
}

struct HomeScreen: View {
    
    var onLogout: () -> Void
    @StateViewModel private var model = HomeViewModel()
    @State private var isLoading: Bool = true
    @State private var path: [HomeDestinations] = []
    @State private var isTagsSectionCollapsed: Bool = false
    
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
                    BookmarksList(filter: BookmarksListFilterAll())
                case .unread:
                    BookmarksList(filter: BookmarksListFilterUnread())
                    EmptyView()
                case .untagged:
                    BookmarksList(filter: BookmarksListFilterUntagged())
                    EmptyView()
                case .trash:
                    BookmarksList(filter: BookmarksListFilterArchived())
                    EmptyView()
                case .tag(let tagName):
                    BookmarksList(filter: BookmarksListFilterTagName(tag: tagName))
                    EmptyView()
                case .search(_):
                    EmptyView()
                case .bookmark(_):
                    EmptyView()
                }
            }
        }
        .searchable(text: Binding(get: {
            model.state.search
        }, set: { searchTerm in
            model.search(term: searchTerm)
        })) {
            Section("find_items") {
                NavigationLink(value: HomeDestinations.search(model.state.search)) {
                    HStack {
                        Image(systemName: "magnifyingglass")
                        Text(model.state.search)
                    }
                }.buttonStyle(PlainButtonStyle())
            }
            Section("home_tags") {
                ForEach(model.state.tagsSearch, id: \.name) { tag in
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
        }
    }
}

struct Previews_HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen { }
    }
}
