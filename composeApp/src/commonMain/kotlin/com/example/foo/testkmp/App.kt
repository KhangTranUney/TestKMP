package com.example.foo.testkmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        var showDemoFlow by remember { mutableStateOf(false) }

        if (showDemoFlow) {
            DemoFlow(onExit = { showDemoFlow = false })
        } else {
            MainScreen(onStartDemoFlow = { showDemoFlow = true })
        }
    }
}

@Composable
private fun MainScreen(onStartDemoFlow: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Main Screen", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Welcome to TestKMP",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onStartDemoFlow) {
            Text("Start Demo Flow")
        }
    }
}
