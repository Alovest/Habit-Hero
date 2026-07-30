package com.example.habithero.presentation.MainScreens.HomeScreen


import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Card
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
import com.example.habithero.R
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.ImageRequest
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
    Column(modifier = Modifier
        .fillMaxSize()
        .background(backColor)) {
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
            val sharedPref = remember { context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE) }
            val saveCount = remember { sharedPref.getInt("counter", 0) }
            var count by remember { mutableStateOf(saveCount) }
            val imageLoader = remember {
                ImageLoader.Builder(context)
                    .components {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                            add(AnimatedImageDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }.build()
            }

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
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .shadow(
                            10.dp,
                            shape = RoundedCornerShape(14.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = colorOfCard)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            //Row(modifier = Modi) { }
                            Text(text = if (count <= 100) "Cчет: $count/100" else "Счет: $count/150",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                modifier = Modifier.padding(start = 80.dp, end = 50.dp, top = 10.dp)
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            AsyncImage(
                                model = if (count <= 100) {
                                    R.drawable.cat_2_phaza
                                } else {
                                    R.drawable.cat
                                },
                                contentDescription = "Cat",
                                imageLoader = imageLoader,
                                modifier = Modifier.padding(30.dp)
                            )
                        }
                    }
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
                                    .heightIn(min = 60.dp)
                                    .shadow(10.dp,
                                        shape = RoundedCornerShape(14.dp)
                                    ),
                                colors = CardDefaults.cardColors(containerColor = colorOfCard),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
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
                                                    if (!checked){
                                                        Notification()
                                                    }
                                                    if (checked){
                                                        count ++
                                                        sharedPref.edit().putInt("counter", count).apply()
                                                    }
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