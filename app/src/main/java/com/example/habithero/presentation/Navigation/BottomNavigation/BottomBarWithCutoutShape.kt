package com.example.habithero.presentation.Navigation.BottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habithero.ui.theme.PurpleOfScreen

class BottomBarWithCutoutShape(
    private val cutoutRadius: Dp,
    private val cornerRadius: Dp
) : Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return with(density){
            val w = size.width
            val h = size.height
            val r = cornerRadius.toPx()
            val cr = cutoutRadius.toPx()

            val base = Path().apply {
                addRoundRect(RoundRect( Rect(0f, 0f, w, h), r, r))
            }

            val cx = w / 2f
            val circle = Path().apply {
                addOval(Rect(cx - cr, -cr, cx + cr, cr))
            }

            val result = Path.combine(PathOperation.Difference, base, circle)

            Outline.Generic(result)
        }
    }
}

@Composable
fun NavBarWithCenterButton(navController: NavController, onCenterButtonClick: () -> Unit){
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxWidth().background(PurpleOfScreen), contentAlignment = Alignment.BottomCenter) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(72.dp),
            shape = BottomBarWithCutoutShape(cutoutRadius = 34.dp, cornerRadius = 14.dp),
            color = Color(0xFF151515),
            shadowElevation = 10.dp
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val items = listOf(
                    Triple("Home", Icons.Default.Games, "Home"),
                    Triple("TodoList", Icons.Default.List, "TodoList"),
                    Triple("Pomodoro", Icons.Default.Timer, "Pomodoro"),
                    Triple("Cart", Icons.Default.ShoppingCart, "Cart")
                )

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    NavItem(
                        label = items[0].first,
                        icon = items[0].second,
                        selected = selectedIndex == 0,
                        route = items[0].third
                    ) {
                        selectedIndex = 0
                        navController.navigate(items[0].third)
                    }

                    NavItem(
                        label = items[1].first,
                        icon = items[1].second,
                        selected = selectedIndex == 1,
                        route = items[1].third
                    ) {
                        selectedIndex = 1
                        navController.navigate(items[1].third)
                    }
                }

                Spacer(modifier = Modifier.width(80.dp))

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    NavItem(
                        label = items[2].first,
                        icon = items[2].second,
                        selected = selectedIndex == 2,
                        route = items[2].third
                    ) {
                        selectedIndex = 2
                        navController.navigate(items[2].third)
                    }

                    NavItem(
                        label = items[3].first,
                        icon = items[3].second,
                        selected = selectedIndex == 3,
                        route = items[3].third
                    ) {
                        selectedIndex = 3
                        navController.navigate(items[3].third)
                    }
                }
            }
        }

        CenterButton(onClick = onCenterButtonClick)

//        Box(
//            modifier = Modifier
//                .offset(y = (-24).dp)
//                .size(72.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(72.dp)
//                    .clip(CircleShape)
//                    .background(
//                        brush = Brush.radialGradient(
//                            colors = listOf(Color(0xFF7C4DFF).copy(alpha = 0.9f), Color(0x00000000)),
//                            center = Offset(36f, 36f),
//                            radius = 80f
//                        )
//                    )
//            )
//
//            Box(
//                modifier = Modifier
//                    .size(56.dp)
//                    .shadow(8.dp, CircleShape)
//                    .clip(CircleShape)
//                    .background(Color.White)
//                    .clickable {
//                        println("Центральная кнопка нажата")
//                    }
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Center Button",
//                    tint = Color(0xFF7C4DFF),
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }

    }
}

@Composable
private fun NavItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    route: String,
    onClick: () -> Unit
) {
    val tint = if (selected) Color(0xFF6E45E8) else Color(0xFF9B9B9B)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = tint, modifier = Modifier.size(22.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = tint, style = MaterialTheme.typography.labelSmall)
    }
}
