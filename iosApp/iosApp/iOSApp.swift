import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        ModuleKt.doInitKoin()
    }
    
    var body: some Scene {
		WindowGroup {
            ContentView()
        }
	}
}
