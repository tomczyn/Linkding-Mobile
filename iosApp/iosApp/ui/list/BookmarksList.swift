//
//  BookmarksList.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 21/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import KMMViewModelSwiftUI
import KMPNativeCoroutinesCombine
import shared
import WebKit

struct BookmarksList: View {
    
    let filter: BookmarksListFilter
    @StateViewModel private var model: BookmarksListViewModel
    
    init(filter: BookmarksListFilter) {
        self.filter = filter
        self._model = StateViewModel(wrappedValue: BookmarksListViewModel(filter: filter))
    }
    
    var body: some View {
        List {
            ForEach(model.state.bookmarks, id: \.id) { bookmark in
                BookmarkView(bookmark: bookmark)
            }
        }
        .listStyle(PlainListStyle())
    }
}

struct BookmarkView: View {
    let bookmark: Bookmark
    @Environment(\.colorScheme) private var colorScheme
    @State private var images: [UIImage?] = []
    
    var body: some View {
        HStack(alignment: .top) {
            VStack(alignment: .leading) {
                Text(bookmark.title ?? bookmark.websiteTitle ?? "")
                    .lineLimit(3)
                    .font(.system(size: 15, weight: .medium, design: .default))
                
                Spacer().frame(height: 4)
                
                Text((bookmark.description_ ?? bookmark.websiteDescription) ?? "")
                    .lineLimit(3)
                    .font(.system(size: 13, weight: .regular, design: .default))
                
                TagCloud(tags: bookmark.tagNames)
                Text("\(bookmark.urlHost) • \(bookmark.formattedDateAdded)")
                    .font(.footnote)
                    .foregroundColor(.gray)
            }
            .padding(.leading, 6)
        }
        .padding(EdgeInsets(top: 6, leading: 0, bottom: 6, trailing: 0))
    }
    
}

