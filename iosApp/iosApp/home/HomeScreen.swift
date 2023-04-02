//
//  HomeScreen.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 26/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct HomeScreen: View {
    
    var onLogout: () -> Void
    @State private var isLoading: Bool = true
    
    struct Destination : Hashable {}
    
    var body: some View {
        NavigationView {
            Group {
                if isLoading {
                    ProgressView()
                } else {
                    
                }
            }
        }
    }
}
