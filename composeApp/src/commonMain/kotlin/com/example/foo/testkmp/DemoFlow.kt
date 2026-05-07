package com.example.foo.testkmp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Item(
    val id: Int,
    val title: String,
    val description: String,
)

private sealed class Screen {
    data object A : Screen()
    data object B : Screen()
    data class C(val item: Item) : Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoFlow(onExit: () -> Unit) {
    var backStack by remember { mutableStateOf(listOf<Screen>(Screen.A)) }
    val current = backStack.last()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (current) {
                            is Screen.A -> "Screen A"
                            is Screen.B -> "Screen B"
                            is Screen.C -> "Item Details"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (backStack.size > 1) {
                                backStack = backStack.dropLast(1)
                            } else {
                                onExit()
                            }
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (current) {
                is Screen.A -> ScreenA(
                    onStartB = { backStack = backStack + Screen.B }
                )
                is Screen.B -> ScreenB(
                    onItemClick = { backStack = backStack + Screen.C(it) }
                )
                is Screen.C -> ScreenC(
                    item = current.item,
                    onClose = onExit,
                )
            }
        }
    }
}

@Composable
private fun ScreenA(onStartB: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Welcome to Screen A", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onStartB) {
            Text("Go to Screen B")
        }
    }
}

@Composable
private fun ScreenB(onItemClick: (Item) -> Unit) {
    val items = remember {
        (1..20).map {
            Item(
                id = it,
                title = "Item $it",
                description = "This is the detailed description for item $it."
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items, key = { it.id }) { item ->
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onItemClick(item) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(item.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun ScreenC(item: Item, onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(item.title, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(item.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onClose,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Close")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}
