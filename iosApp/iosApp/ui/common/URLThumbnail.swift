//
//  URLThumbnail.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 25/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import WebKit

struct WebView: UIViewRepresentable {
    let url: URL
    @Binding var loaded: Bool
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator
        webView.load(URLRequest(url: url))
        return webView
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {}
    
    class Coordinator: NSObject, WKNavigationDelegate {
        var parent: WebView
        
        init(_ parent: WebView) {
            self.parent = parent
        }
        
        func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
            parent.loaded = true
        }
    }
}


struct URLThumbnail: View {
    let url: String
    @State private var thumbnail: UIImage? = nil
    @State private var loaded: Bool = false
    
    var body: some View {
        if let thumbnail = thumbnail {
            Image(uiImage: thumbnail)
                .resizable()
                .frame(width: 80, height: 80)
        } else {
            WebView(url: URL(string: url)!, loaded: $loaded)
                .frame(width: 0, height: 0)
                .onChange(of: loaded) { isLoaded in
                    if isLoaded {
                        captureThumbnail()
                    }
                }
        }
    }
    
    private func captureThumbnail() {
        let webView = WKWebView()
        webView.load(URLRequest(url: URL(string: url)!))
        
        webView.evaluateJavaScript("document.documentElement.scrollHeight") { result, error in
            guard let height = result as? CGFloat, error == nil else {
                print("Error retrieving scroll height: \(String(describing: error))")
                return
            }
            
            let configuration = WKSnapshotConfiguration()
            configuration.rect = CGRect(x: 0, y: 0, width: webView.bounds.width, height: height)
            
            webView.takeSnapshot(with: configuration) { image, error in
                if let image = image {
                    thumbnail = image
                } else if let error = error {
                    print("Error taking snapshot: \(error)")
                }
            }
        }
    }
}


extension NSNotification.Name {
    static let webViewDidFinishLoad = NSNotification.Name("webViewDidFinishLoad")
}
