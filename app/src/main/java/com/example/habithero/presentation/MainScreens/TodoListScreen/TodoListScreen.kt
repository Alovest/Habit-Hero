package com.example.habithero.presentation.MainScreens.TodoListScreen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.habithero.infrastructure.data.Room.Data.TodoList
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.ui.theme.backColor
import com.example.habithero.ui.theme.colorOfCard
import org.koin.androidx.compose.koinViewModel
@Composable
fun TodoListScreen(viewModel: TodoListViewModel, navController: NavController) {
    val todoList by viewModel.readAllData.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
Box(modifier = Modifier.background(backColor).fillMaxSize()) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(todoList) { items ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(start = 12.dp, end = 6.dp, top = 8.dp)
                    .shadow(
                        10.dp,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable {
                        if (items.titleOfTodo.isNotBlank()) {
                            navController.navigate("details/${items.titleOfTodo}")
                        } else {
                            Log.d("data is not sending", "data: ${items.titleOfTodo}")
                        }
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorOfCard),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment
                            .CenterVertically
                    ) {
                        Text(
                            text = items.titleOfTodo,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }
        }
    }
Box(modifier = Modifier.align(BottomEnd)) {
    FlActionBtn(
        onClick = { showDialog = true },
        modifier = Modifier
            .padding(16.dp)
    )
}
    if (showDialog) {
        showDialogFun(
            onDismiss = { showDialog = false },
            viewModel = viewModel
        )
    }
}
}

@Composable
fun FlActionBtn(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(Icons.Filled.Add, "Floating action button")
    }
}

@Composable
fun showDialogFun(
    onDismiss: () -> Unit,
    viewModel: TodoListViewModel
) {
    var inputName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Создайте папку") },
        text = {
            Column {
                Text("Введите название папки:")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    singleLine = true,
                    placeholder = { Text("Название дела") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (inputName.isNotBlank()) {
                    val newTodo = TodoList(titleOfTodo = inputName)
                    viewModel.getTodoFromUser(newTodo)
                    inputName = ""
                    onDismiss()
                }
            }) {
                Text("Создать")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Отмена")
            }
        }
    )
}
