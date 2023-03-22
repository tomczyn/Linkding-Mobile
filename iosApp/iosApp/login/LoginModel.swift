//
//  LoginModel.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 22/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class LoginModel: ObservableObject {
    @Published var isLoading: Bool = false
    
    func login(host: String, token: String) {
        isLoading = true
        // Perform your login logic here
        // ...
        isLoading = false
    }
}
