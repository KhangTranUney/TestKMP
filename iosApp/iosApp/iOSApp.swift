import SwiftUI

@main
struct iOSApp: App {
    @State private var showLoginView = false

    var body: some Scene {
        WindowGroup {
            SplashScreen(onFinished: {
                showLoginView = true
            })
            .fullScreenCover(isPresented: $showLoginView) {
                LoginView(onExit: {
                    showLoginView = false
                })
            }
        }
    }
}
