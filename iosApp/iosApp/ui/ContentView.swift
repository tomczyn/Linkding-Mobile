//
//  ContentView.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 02/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ContentView: View {
    
    @State private var isUserLoggedIn: Bool
    @State private var path = NavigationPath()
    
    init() {
        let settings = KoinHelper().settings
        self._isUserLoggedIn = State(initialValue: settings.isUserLoggedIn)
    }
    
    var body: some View {
        if isUserLoggedIn {
            NavigationStack(path: $path) {
                HomeScreen(onLogout: {
                    isUserLoggedIn = false
                })
//                .navigationDestination(for: MyScreen.Destination.self) { destination in
//                }
            }
        } else {
            LoginScreen(onLogin: {
                isUserLoggedIn = true
            })
        }
    }
}
