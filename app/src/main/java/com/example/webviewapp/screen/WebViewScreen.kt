package com.example.webviewapp.screen

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen() {
    val context = LocalContext.current
    val url = remember { mutableStateOf("https://newsnowbharatvarsh.com/") }
    val buttonState = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(true) }

    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    isLoading.value = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    isLoading.value = false
                }
            }
            loadUrl(url.value)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { webView },
            modifier = Modifier
                .fillMaxSize()
        )
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Transparent, shape = CircleShape)
                    .alpha(0.8f)
                    .align(Alignment.Center),
                color = Color.Black
            )
        }
    }
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(
                        if (buttonState.value == "back") buttonColorState(
                            "back",
                            buttonState
                        ) else Color.Transparent,
                        shape = CircleShape
                    ),
                onClick = {
                    if (webView.canGoBack()) {
                        webView.goBack()
                        buttonState.value = "back"
                    }

                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(
                        if (buttonState.value == "home") buttonColorState(
                            "home",
                            buttonState
                        ) else Color.Transparent,
                        shape = CircleShape
                    ),
                onClick = {
                    webView.loadUrl(url.value)
                    buttonState.value = "home"
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(
                        if (buttonState.value == "forward") buttonColorState(
                            "forward",
                            buttonState
                        ) else Color.Transparent, shape = CircleShape
                    ),
                onClick = {
                    if (webView.canGoForward()) {
                        webView.goForward()
                        buttonState.value = "forward"
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward",
                    tint = Color.White
                )
            }
        }
    }

}

fun buttonColorState(state: String, buttonState: MutableState<String>): Color {
    buttonState.value = " "
    return when (state) {
        "home",
        "back",
        "forward" ->
            Color.White

        else -> Color.Transparent
    }
}


