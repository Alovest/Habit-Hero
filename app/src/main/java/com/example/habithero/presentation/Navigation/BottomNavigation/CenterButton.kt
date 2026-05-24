package com.example.habithero.presentation.Navigation.BottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CenterButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .clickable { onClick() }
            .offset(y = (-24).dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFF7C4DFF).copy(alpha = 0.9f), Color(0x00000000)),
                        center = Offset(36f, 36f),
                        radius = 80f
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(56.dp)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Center Button",
                tint = Color(0xFF7C4DFF),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}