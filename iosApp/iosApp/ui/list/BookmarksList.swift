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
    
    let tag: String
    @StateViewModel private var model: BookmarksListViewModel
    
    init(tag: String) {
        self.tag = tag
        self._model = StateViewModel(wrappedValue: BookmarksListViewModel(tag: tag))
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
    @State private var images = [String: UIImage]()
    @State private var webView: WKWebView?
    private let webViewDelegate = WebViewDelegate()

    
    var body: some View {
        HStack(alignment: .top) {
            if let image = images[bookmark.url] {
                Image(uiImage: image)
                    .resizable()
                    .cornerRadius(4)
                    .background(.red)
                    .aspectRatio(contentMode: .fill)
                    .frame(width: 80, height: 80)
                    .clipped()
            } else {
                Rectangle()
                    .fill(Color.gray)
                    .cornerRadius(4)
                    .opacity(0.1)
                    .frame(width: 80, height: 80)
                    .clipped()
                    .onAppear {
                        loadWebsite(url: bookmark.url)
                    }
            }
            
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
    
    
    private func loadWebsite(url: String) {
        guard let url = URL(string: url) else {
            print("Invalid URL: \(url)")
            return
        }
        
        let webView = self.webView ?? WKWebView()
        webView.navigationDelegate = webViewDelegate
        webView.load(URLRequest(url: url))
        self.webView = webView
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            takeScreenshot(webView: webView, url: url.absoluteString)
        }
    }
    
    private func takeScreenshot(webView: WKWebView, url: String) {
        let configuration = WKSnapshotConfiguration()
        configuration.snapshotWidth = 80
        
        webView.takeSnapshot(with: configuration) { image, error in
            if let image = image {
                images[url] = image
            }
        }
    }
    
    
    
}

class WebViewDelegate: NSObject, WKNavigationDelegate {
    func webView(_ webView: WKWebView, didFailProvisionalNavigation navigation: WKNavigation!, withError error: Error) {
        print("Failed to load URL with error: \(error.localizedDescription)")
    }
    
    func webView(_ webView: WKWebView, didFail navigation: WKNavigation!, withError error: Error) {
        print("Failed to load URL with error: \(error.localizedDescription)")
    }
}
