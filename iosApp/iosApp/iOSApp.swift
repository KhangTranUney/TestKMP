import SwiftUI

@main
struct iOSApp: App {
    @State private var showDemoView = false

    var body: some Scene {
        WindowGroup {
            SplashScreen(onFinished: {
                showDemoView = true
            })
            .fullScreenCover(isPresented: $showDemoView) {
                DemoView()
            }
        }
    }
}
