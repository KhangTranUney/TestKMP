import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        DemoViewControllerKt.DemoViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct DemoView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea()
    }
}
