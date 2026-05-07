package com.example.foo.testkmp

import androidx.compose.ui.window.ComposeUIViewController

fun DemoViewController(onExit: () -> Unit) = ComposeUIViewController { App(onExit = onExit) }
