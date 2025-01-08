package org.example.yarldemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.yarldemo.di.BaseComponentImpl
import org.example.yarldemo.di.createBaseComponent
import org.example.yarldemo.shared.YRALReelPlayer
import org.example.yarldemo.shared.core.di.PlatformComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(platformComponent: PlatformComponent) {
    val baseComponent = buildBaseComponent(platformComponent)
    MaterialTheme {
        var showContent by remember { mutableStateOf(true) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(showContent) {
                YRALReelPlayer(videoUrlArray = listOf(
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/76588af8fb5242fdbdd4574a12b81fe4/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/11ade26f9d8e4f0e928d625d799675ab/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/8d9cc69db22542f69249ea17f2bcf87f/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/1b25de7cd259461d96bf287eab4d622d/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/5efd1768a03b446ea7fbd6fcfc34b0a3/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/cef28c7a57af4385b4d165e38dea9524/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/5b3e50ea52c34e5fafb998724337ac3b/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/1ebe593e28b147a48903256115c355e2/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/1eb6c9edd6734b4695cd01fb09a1a10b/downloads/default.mp4",
                    "https://customer-2p3jflss4r4hmpnz.cloudflarestream.com/6435e6983473462a91b186e085a69535/downloads/default.mp4"
                ))
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