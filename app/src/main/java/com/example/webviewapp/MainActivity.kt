package com.example.webviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.webviewapp.screen.WebViewScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val isNavigateState = remember { mutableStateOf(false) }

            if (isNavigateState.value) {
                WebViewScreen()
            } else {
                SplashScreen {
                    isNavigateState.value = !isNavigateState.value
                }
            }
        }
    }
}



