package com.example.foo.testkmp

import androidx.compose.ui.window.ComposeUIViewController

fun LoginViewController(onExit: () -> Unit) = ComposeUIViewController { App(onExit = onExit) }
