package com.example.dextify.presentation.loadingscreen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

/*
 * LoadingScreen
 * -------------
 * Animated Pokéball loading screen.
 * Handles entrance, glow burst, split exit, flash effect,
 * and transitions to the next screen when finished.
 */

@Preview(showBackground = true, backgroundColor = 0xFF1A1A2E)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(onAnimationFinished = {})
}


@Composable
fun PokeballTopHalf(
    modifier: Modifier = Modifier,
    topColor: Color = Color(0xFFEF5350),
    borderColor: Color = Color(0xFF333333)
) {
    Canvas(modifier = modifier) {
        val diameter = min(size.width, size.height)
        val radius = diameter / 2f
        val center = Offset(size.width / 2f, size.height / 2f)
        val topLeft = Offset(center.x - radius, center.y - radius)
        val ballSize = Size(diameter, diameter)

        drawArc(
            color = topColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = topLeft,
            size = ballSize
        )
        drawArc(
            color = borderColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = topLeft,
            size = ballSize,
            style = Stroke(width = radius * 0.06f)
        )
    }
}

@Composable
fun PokeballBottomHalf(
    modifier: Modifier = Modifier,
    bottomColor: Color = Color.White,
    borderColor: Color = Color(0xFF333333)
) {
    Canvas(modifier = modifier) {
        val diameter = min(size.width, size.height)
        val radius = diameter / 2f
        val center = Offset(size.width / 2f, size.height / 2f)
        val topLeft = Offset(center.x - radius, center.y - radius)
        val ballSize = Size(diameter, diameter)

        drawArc(
            color = bottomColor,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = topLeft,
            size = ballSize
        )
        drawArc(
            color = borderColor,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = topLeft,
            size = ballSize,
            style = Stroke(width = radius * 0.06f)
        )
    }
}

@Composable
fun PokeballCenter(
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0xFF333333),
    glowAlpha: Float = 0f
) {
    Canvas(modifier = modifier) {
        val diameter = min(size.width, size.height)
        val radius = diameter / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        drawLine(
            color = borderColor,
            start = Offset(center.x - radius, center.y),
            end = Offset(center.x + radius, center.y),
            strokeWidth = radius * 0.06f
        )
        drawCircle(
            color = Color(0xFFFFEB3B).copy(alpha = glowAlpha * 0.15f),
            radius = radius / 2.8f,
            center = center
        )
        drawCircle(
            color = Color(0xFFFFD700).copy(alpha = glowAlpha * 0.35f),
            radius = radius / 3.5f,
            center = center
        )
        drawCircle(
            color = borderColor,
            radius = radius / 4f,
            center = center
        )
        drawCircle(
            color = Color.White,
            radius = radius / 6f,
            center = center
        )
    }
}

/* ---------------- Screen Animation ---------------- */
@Composable
fun LoadingScreen(
    onAnimationFinished: () -> Unit
) {
    val scaleEntrance = remember { Animatable(0.6f) }
    val alphaEntrance = remember { Animatable(0f) }
    val glowAlpha     = remember { Animatable(0f) }

    val topOffsetY    = remember { Animatable(0f) }
    val bottomOffsetY = remember { Animatable(0f) }
    val exitFade      = remember { Animatable(1f) }

    val flashAlpha = remember { Animatable(0f) }

    val textAlpha   = remember { Animatable(1f) }
    val textOffsetY = remember { Animatable(0f) }

    var screenHeight by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {

        // Entrance
        launch {
            scaleEntrance.animateTo(1f, tween(500, easing = FastOutSlowInEasing))
        }
        alphaEntrance.animateTo(1f, tween(500, easing = FastOutSlowInEasing))

        // Static hold
        delay(1200L)

        //Glow burst + scale
        launch {
            glowAlpha.animateTo(1f, tween(350, easing = FastOutSlowInEasing))
            glowAlpha.animateTo(0f, tween(350, easing = FastOutSlowInEasing))
        }
        launch {
            scaleEntrance.animateTo(1.08f, tween(200, easing = FastOutSlowInEasing))
            scaleEntrance.animateTo(1f,    tween(200, easing = FastOutSlowInEasing))
        }

        delay(750L)

        // Flash
        launch {
            flashAlpha.animateTo(0.18f, tween(120, easing = LinearEasing))
            flashAlpha.animateTo(0f,    tween(300, easing = FastOutSlowInEasing))
        }

        val exitDistance = if (screenHeight > 0f) screenHeight * 0.75f else 600f

        // Exit split
        launch { topOffsetY.animateTo(-exitDistance,   tween(420, easing = FastOutSlowInEasing)) }
        launch { bottomOffsetY.animateTo(exitDistance, tween(420, easing = FastOutSlowInEasing)) }
        launch { exitFade.animateTo(0f,                tween(380, easing = FastOutSlowInEasing)) }
        launch { textAlpha.animateTo(0f,               tween(300, easing = FastOutSlowInEasing)) }
        launch { textOffsetY.animateTo(30f,            tween(300, easing = FastOutSlowInEasing)) }

        delay(420L)
        onAnimationFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A2E))
            .clipToBounds()
            .onSizeChanged { size -> screenHeight = size.height.toFloat() },
        contentAlignment = Alignment.Center
    ) {

        // Pokéball
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    scaleX = scaleEntrance.value
                    scaleY = scaleEntrance.value
                    alpha  = alphaEntrance.value
                }
        ) {
            PokeballTopHalf(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { translationY = topOffsetY.value }
            )
            PokeballBottomHalf(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { translationY = bottomOffsetY.value }
            )
            PokeballCenter(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = exitFade.value },
                glowAlpha = glowAlpha.value
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = flashAlpha.value }
                .background(Color.White)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
                .graphicsLayer {
                    alpha        = textAlpha.value
                    translationY = textOffsetY.value
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "v.0.0.4",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "by BeagitNet",
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        }
    }
}
