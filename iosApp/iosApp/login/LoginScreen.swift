//
//  LoginScreen.swift
//  iosApp
//
//  Created by Maciej Tomczyński on 22/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import KMMViewModelSwiftUI
import shared

struct LoginScreen: View {
    
    @State private var host: String = ""
    @State private var token: String = ""
    @StateViewModel private var model = LoginViewModel()

    var body: some View {
        ZStack {
            ZStack {
                VStack {
                    Text("Login")
                        .font(.largeTitle)
                        .frame(maxWidth: .infinity)
                    Spacer()
                    TextField("Host", text: $host)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .autocapitalization(.none)
                        .disableAutocorrection(true)
                    Spacer().frame(height: 30)
                    SecureField("Token", text: $token)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                    Spacer()
                    Button(action: { model.login(host:host, token:token) }) {
                        Text("Login")
                            .foregroundColor(.white)
                            .frame(width: 200.0)
                            .padding()
                            .background(Color.blue)
                            .cornerRadius(8.0)
                    }
                }
            }
            .padding()
            // Disable user interaction when loading
            .allowsHitTesting(!model.loginState.isLoading)
            if model.loginState.isLoading {
                Color.black.opacity(0.5).ignoresSafeArea()
                ProgressView()
            }
        }
    }
}
