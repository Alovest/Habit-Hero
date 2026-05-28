package com.example.habithero.presentation.MainScreens.HomeScreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.habithero.infrastructure.WorkManager.NotifyWorker
import com.example.habithero.infrastructure.data.Room.Data.User
import com.example.habithero.presentation.ViewModel.UserViewModel
import com.example.habithero.ui.theme.backColor
import com.example.habithero.ui.theme.colorOfCard
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import kotlin.collections.emptyList


@Composable
fun HomeScreen(){
    Column(modifier = Modifier.fillMaxSize().background(backColor)) {
        CardsOfScreens()
    }
}
        @Composable
        fun CardsOfScreens() {
            val viewModel: UserViewModel = koinViewModel()
            val users by viewModel.readAllData.observeAsState(emptyList())
            var expandedUserId by remember { mutableStateOf<Int?>(null) }
            var localWasCheckedDate by remember { mutableStateOf(LocalDate.now()) }
            var isChecked by remember { mutableStateOf(false) }
            val context = LocalContext.current
            fun Notification(){
                val notificationWork: WorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
                    .setInitialDelay(1, TimeUnit.SECONDS)
                    .build()
                WorkManager.getInstance(context).enqueue(notificationWork)
            }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .shadow(6.dp, shape = RoundedCornerShape(16.dp))
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorOfCard)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                ) {
                    Text(
                        text = "Ваши привычки:",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(users, key = { it.id }) { user ->
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                                    .shadow(10.dp, shape = RoundedCornerShape(14.dp))
                                    .heightIn(min = 60.dp), // Минимальная высота вместо жёсткой
                                elevation = 8.dp,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(colorOfCard)
                                        .padding(16.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = user.title,
                                            color = if (user.isChecked) Color.Gray else Color.White,
                                            style = MaterialTheme.typography.titleMedium,
                                            textDecoration = if (user.isChecked) TextDecoration.LineThrough else TextDecoration.None
                                        )

                                        LaunchedEffect(localWasCheckedDate){
                                            if (LocalDate.now().isAfter(localWasCheckedDate)) {
                                                viewModel.updateUserChecked(user.id, false)
                                            }
                                        }

                                        Row {
                                            Checkbox(
                                                checked = user.isChecked,
                                                onCheckedChange = { checked ->
                                                    viewModel.updateUserChecked(user.id, checked)
                                                    if (!user.isChecked && checked) {
                                                        localWasCheckedDate = LocalDate.now()
                                                    }
//                                                    if (!checked){
//                                                        Notification()
//                                                    }
                                                },
                                                colors = CheckboxDefaults.colors(
                                                    checkedColor = Color.DarkGray,
                                                    uncheckedColor = Color.Gray,
                                                    checkmarkColor = Color.White
                                                )
                                            )

                                            IconButton(onClick = { expandedUserId = user.id }) {
                                                Icon(
                                                    Icons.Default.MoreVert,
                                                    contentDescription = "Меню действий",
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                                DropdownMenu(
                                    expanded = expandedUserId == user.id,
                                    onDismissRequest = { expandedUserId = null },
                                ) {
                                    DropdownMenuItem(
                                        onClick = {
                                            Log.d("Dropdown", "Удаление привычки: ${user.title}")
                                            viewModel.deleteUsersHabit(user)
                                            expandedUserId = null
                                        },
                                        text = { Text("Удалить") }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }