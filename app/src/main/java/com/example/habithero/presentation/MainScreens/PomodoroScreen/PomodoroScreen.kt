package com.example.habithero.presentation.MainScreens.PomodoroScreen

import android.inputmethodservice.Keyboard
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


@Composable
fun Pomodoro() {
SimpleCountdownTimer()
}

//@Composable
//fun CircleProgressIndicator(){
//    var currentProgress by remember { mutableStateOf(0f) }
//    var loading by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//
//    Column(
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Button(onClick = {
//            loading = true
//            scope.launch {
//                loadProgress {progress ->
//                    currentProgress = progress
//                }
//                loading = false
//            }
//        }, enabled = !loading) {
//            Text("Start loading")
//        }
//        if (loading){
//            CircularProgressIndicator(
//                progress =  {currentProgress},
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//    }
//}
//
//suspend fun loadProgress(updateProgress: (Float) -> Unit) {
//    for (i in 1..6000) {
//        updateProgress(i.toFloat() / 100)
//        delay(100)
//    }
//}
@Composable
fun SimpleCountdownTimer(initialSeconds: Int = 10) {
    var seconds by remember { mutableStateOf(initialSeconds) }
    var isRunning by remember { mutableStateOf(false) }

    // Запускаем корутину для отсчета
    LaunchedEffect(key1 = isRunning) {
        if (isRunning && seconds > 0) {
            while (seconds > 0 && isActive) { // isActive проверяет, не была ли корутина отменена
                delay(1000) // Ждем 1 секунду
                seconds--
            }
            if (seconds == 0) {
                isRunning = false // Остановка таймера, когда достигнет 0
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Время: $seconds", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(32.dp))

        Row() {
            Button(
                onClick = { isRunning = !isRunning },
                enabled = seconds > 0 || !isRunning
            ) {
                Text(if (isRunning) "Пауза" else "Старт")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    seconds = initialSeconds
                    isRunning = false
                },
                enabled = !isRunning
            ) {
                Text("Сброс")
            }
        }
    }
}