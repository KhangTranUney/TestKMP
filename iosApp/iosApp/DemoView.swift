import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    let onExit: () -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        DemoViewControllerKt.DemoViewController(onExit: onExit)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct DemoView: View {
    let onExit: () -> Void

    var body: some View {
        ComposeView(onExit: onExit)
            .ignoresSafeArea()
    }
}
