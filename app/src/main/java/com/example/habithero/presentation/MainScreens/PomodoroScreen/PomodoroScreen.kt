package com.example.habithero.presentation.MainScreens.PomodoroScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habithero.ui.theme.backColor
import kotlinx.coroutines.delay

@Composable
fun Pomodoro() {
 var isWorkMode by remember { mutableStateOf(true) }
 var progress by remember {mutableStateOf(0f)}

    LaunchedEffect(Unit){
       val totalTime = if (isWorkMode) 300 else 150

        for (i in totalTime downTo 0) {
            delay(1000)
            progress = (totalTime - i).toFloat() / totalTime
        }
    }

 Column(modifier = Modifier
     .fillMaxSize()
     .background(backColor),
     verticalArrangement = Arrangement.Center,
     horizontalAlignment = Alignment.CenterHorizontally) {
     Row(modifier = Modifier.padding(16.dp))
     {
         Text(
            text = "Работа",
            color = if (isWorkMode)
                  Color.White else Color.Gray,
            modifier = Modifier
                  .clickable { isWorkMode = true }
                .padding(16.dp)
                        )
         Text(
             text = "Отдых",
             color = if (!isWorkMode)
             Color.White else Color.Gray,
             modifier = Modifier
                 .clickable { isWorkMode = false }
                 .padding(16.dp)
         )
     }

   CircularProgressIndicatorIP(
       progress = progress,
       modifier = Modifier.size(200.dp)
   )

     Text(
         text = "5:00",
         fontSize = 48.sp,
         fontWeight = FontWeight.Bold,
         color = Color(0xFFE6F7F6),
         modifier = Modifier
             .padding(top = 16.dp)
     )

     Text(
         text = "Время ${if (isWorkMode)
         "работы" else "отдыха"}",
         fontSize = 16.sp,
         color = Color(0x99FFFFFF),
         modifier = Modifier.padding(top = 8.dp)
     )

     Text("Запустить таймер")
     Spacer(modifier = Modifier.height(20.dp))
     Button(onClick = {
         isWorkMode = !isWorkMode
     }) {
         Text("Сменить режим")
     }
 }

}

@Composable
fun CircularProgressIndicatorIP(progress: Float, modifier: Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 12f
        val radius = size.maxDimension / 2 - strokeWidth / 2

        drawCircle(color = Color(0x1AFFFFFF),
            radius = radius)

        drawArc(
            color = Color(0xFF14E6D6),
            startAngle = -90f,
            sweepAngle = progress * 360f,
            useCenter = false,
            style = Stroke(strokeWidth),
            size = size.copy(width = radius * 2,
                height = radius * 2),
            topLeft = center.copy(x = center.x - radius, y = center.y - radius)
        )
    }
}