package com.example.habithero.presentation.MainScreens.TodoListScreen

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.habithero.infrastructure.data.Room.Data.InterPackages
import com.example.habithero.infrastructure.data.Room.Data.TodoList
import com.example.habithero.presentation.ViewModel.InterPackViewModel
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.ui.theme.backColor
import com.example.habithero.ui.theme.colorOfCard
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.nio.file.WatchEvent


@Composable
fun TodoListOfPackage(
    todoList: TodoList,
    folderId: Long,
    navController: NavController
) {
    val viewModel: InterPackViewModel = koinViewModel(parameters = { parametersOf(folderId) })

    val interPackList by viewModel.interPackList.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(backColor)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .heightIn(min = 30.dp),
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    ),
                ) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopStart) {
                        Icon(
                            Icons.Default.ArrowBack,
                            "back to folders",
                            tint = Color.White)
                    }
                }
                Text(
                    text = "Папка ${todoList.titleOfTodo}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 45.dp)
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(interPackList) { item ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                        .heightIn(
                            min = 50.dp,
                        )
                        .shadow(
                            10.dp,
                            shape = RoundedCornerShape(14.dp)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = colorOfCard)
                    ) {
                        Box(
                            modifier = Modifier
                            .fillMaxSize()
                        ) {
                            Text(
                                text = item.titleOfInterPackages,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            FloatingActionBtn(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    if (showDialog) {
        ShowDialogFun(
            folderId = folderId, // FIXED: Pass the verified folderId argument directly
            onDismiss = { showDialog = false },
            viewModel = viewModel
        )
    }
}

@Composable
fun FloatingActionBtn(
    onClick: () -> Unit,
    modifier: Modifier
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            Icons.Filled.Add,
            "Floating Action Button"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogFun(
    folderId: Long, // ID папки
    onDismiss: () -> Unit,
    viewModel: InterPackViewModel
) {
    var inputName by remember { mutableStateOf("") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Создайте задачу") },
        text = {
            TextField(
                value = inputName,
                onValueChange = { inputName = it },
                singleLine = true,
                placeholder = { Text("Название задачи") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(onClick = {
                val trimmedName = inputName.trim()

                // КРИТИЧЕСКАЯ ПРОВЕРКА: Проверяем, что ID папки пришел корректным
                if (folderId == 0L) {
                    android.util.Log.e("ROOM_CRITICAL_ERROR", "Попытка сохранить InterPackages с folderId = 0L! Операция заблокирована.")
                    Toast.makeText(context, "Ошибка: Неверный ID папки (0)", Toast.LENGTH_LONG).show()
                    return@Button
                }

                if (trimmedName.isNotBlank()) {
                    val newItem = InterPackages(
                        titleOfInterPackages = trimmedName,
                        folderId = folderId
                    )

                    android.util.Log.d("ROOM_SUCCESS_CHECK", "Отправка в базу: задача=$trimmedName, folderId=$folderId")

                    viewModel.addInterPackViewModel(newItem)
                    inputName = ""
                    onDismiss()
                }
            }) {
                Text("Создать")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Закрыть")
            }
        }
    )
}

