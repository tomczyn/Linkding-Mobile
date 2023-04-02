import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @State private var isUserLoggedIn: Bool
    @State private var path = NavigationPath()
    
    init() {
        ModuleKt.doInitKoin()
        let settings = KoinHelper().settings
        self._isUserLoggedIn = State(initialValue: settings.isUserLoggedIn)
    }
    
    var body: some Scene {
		WindowGroup {
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
}
