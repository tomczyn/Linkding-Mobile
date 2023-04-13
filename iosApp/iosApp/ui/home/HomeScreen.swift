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
}

struct HomeScreen: View {
    
    var onLogout: () -> Void
    @State private var isLoading: Bool = true
    @State private var path: [HomeDestinations] = []
    
    var body: some View {
        NavigationStack(path: $path) {
            List {
//                NavigationLink("All items", value: .allItems)
////                NavigationLink("Unread", value: .unread)
            }
            .navigationDestination(for: HomeDestinations.self) { destination in
                switch destination {
                    case .allItems:
                        EmptyView()
                    case .unread:
                        EmptyView()
                }
            }
        }
    }
}

struct Previews_HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        /*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
    }
}
