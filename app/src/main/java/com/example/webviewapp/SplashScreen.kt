package com.example.webviewapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onAnimationFinished: () -> Unit) {
    val animationProgress = remember { mutableStateOf(0f) }



    LaunchedEffect(Unit) {
        animationProgress.value = 1f
        delay(2000)
        onAnimationFinished()
    }

    val scale = animateFloatAsState(
        targetValue = animationProgress.value,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.newlogo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .scale(scale.value),
            contentScale = ContentScale.Fit
        )
        DisposableEffect(animationProgress) {
            if (animationProgress.value == 1f) {
                onAnimationFinished()
            }
            onDispose { }
        }
    }
}