import SwiftUI

struct SplashScreen: View {
    @State private var countdown = 3
    @State private var timer: Timer?
    @State private var finished = false

    let onStart: () -> Void

    var body: some View {
        VStack(spacing: 24) {
            Spacer()

            Text("TestKMP")
                .font(.system(size: 36, weight: .bold, design: .rounded))

            Text("A Kotlin Multiplatform demo app")
                .font(.subheadline)
                .foregroundStyle(.secondary)

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
