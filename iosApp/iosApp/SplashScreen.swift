import SwiftUI
import UIKit

struct SplashScreen: View {
    @State private var countdown = 3
    @State private var timer: Timer?
    @State private var finished = false

    let onStart: () -> Void

    var body: some View {
        VStack(spacing: 24) {
            Spacer()

            if let uiImage = UIImage(named: "AppIcon") {
                Image(uiImage: uiImage)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 120, height: 120)
                    .clipShape(RoundedRectangle(cornerRadius: 24))
            } else {
                Image(systemName: "app.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 120, height: 120)
                    .foregroundStyle(.blue)
            }

            if finished {
                Button(action: onStart) {
                    Text("Start")
                        .font(.headline)
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .clipShape(RoundedRectangle(cornerRadius: 12))
                }
                .padding(.horizontal, 48)
                .transition(.opacity.combined(with: .scale))
            } else {
                Text("\(countdown)")
                    .font(.system(size: 48, weight: .bold, design: .rounded))
                    .monospacedDigit()
                    .contentTransition(.numericText())
            }

            Spacer()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(.systemBackground))
        .onAppear {
            startCountdown()
        }
        .onDisappear {
            timer?.invalidate()
            timer = nil
        }
    }

    private func startCountdown() {
        timer = Timer.scheduledTimer(withTimeInterval: 1, repeats: true) { t in
            if countdown > 1 {
                withAnimation {
                    countdown -= 1
                }
            } else {
                t.invalidate()
                timer = nil
                withAnimation {
                    finished = true
                }
            }
        }
    }
}
