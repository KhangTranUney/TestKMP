import SwiftUI

@main
struct iOSApp: App {
    @State private var showSplash = true

    var body: some Scene {
        WindowGroup {
            if showSplash {
                SplashScreen(onFinished: {
                    withAnimation {
                        showSplash = false
                    }
                })
            } else {
                DemoView()
            }
        }
    }
}
