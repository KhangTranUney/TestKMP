package com.example.foo.testkmp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute

data class Item(
    val id: Int,
    val title: String,
    val description: String,
)

private val demoItems = (1..20).map {
    Item(
        id = it,
        title = "Item $it",
        description = "This is the detailed description for item $it."
    )
}

fun NavGraphBuilder.demoFlow(navController: NavController) {
    navigation<DemoRoute>(startDestination = DemoScreenARoute) {
        composable<DemoScreenARoute> {
            DemoScreenA(
                onBack = { navController.popBackStack() },
                onStartB = { navController.navigate(DemoScreenBRoute) },
            )
        }
        composable<DemoScreenBRoute> {
            DemoScreenB(
                onBack = { navController.popBackStack() },
                onItemClick = { item -> navController.navigate(DemoScreenCRoute(item.id)) },
            )
        }
        composable<DemoScreenCRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<DemoScreenCRoute>()
            val item = remember(route.itemId) { demoItems.first { it.id == route.itemId } }
            DemoScreenC(
                item = item,
                onBack = { navController.popBackStack() },
                onClose = { navController.popBackStack<MainRoute>(inclusive = false) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoScreenA(onBack: () -> Unit, onStartB: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen A") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoScreenB(onBack: () -> Unit, onItemClick: (Item) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen B") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(demoItems, key = { it.id }) { item ->
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoScreenC(item: Item, onBack: () -> Unit, onClose: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Item Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
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
}
