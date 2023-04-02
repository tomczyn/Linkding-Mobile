//
//  LoginModel.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 31/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesCore
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCombine

class LoginModel : LoginViewModel {
    @Published var goToHome: Bool = false
}
