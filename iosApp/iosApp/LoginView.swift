import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    let onExit: () -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        LoginViewControllerKt.LoginViewController(onExit: onExit)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct LoginView: View {
    let onExit: () -> Void

    var body: some View {
        ComposeView(onExit: onExit)
            .ignoresSafeArea()
    }
}
