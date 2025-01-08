package org.example.yarldemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.yarldemo.di.BaseComponentImpl
import org.example.yarldemo.di.createBaseComponent
import org.example.yarldemo.shared.core.di.PlatformComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import yraldemo.composeapp.generated.resources.Res
import yraldemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(platformComponent: PlatformComponent) {
    val baseComponent = buildBaseComponent(platformComponent)
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { "Hello World!" }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun buildBaseComponent(platformComponent: PlatformComponent): BaseComponentImpl {
    val toastHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    val component = createBaseComponent(navController, toastHostState, platformComponent)
    return component
}