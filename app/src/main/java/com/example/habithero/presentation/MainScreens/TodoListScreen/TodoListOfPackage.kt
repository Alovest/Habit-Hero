package com.example.habithero.presentation.MainScreens.TodoListScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
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
import java.nio.file.WatchEvent

@Composable
fun TodoListOfPackage(todoList: TodoList, navController: NavController, viewModel: InterPackViewModel = koinViewModel()){
    val interPackList by viewModel.readAllData.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().background(backColor)) {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(
                    text = "Папка ${todoList.titleOfTodo}",
                    style = androidx.compose.material.MaterialTheme.typography.h5,
                    color = Color.White
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(interPackList) { item ->
                    Text(
                        text = item.titleOfInterPackages
                    )
                }
            }
        }
        Box(modifier = Modifier.align(BottomEnd)) {
            FloatingActionBtn(
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }

    if (showDialog){
        ShowDialogFun(
            onDismiss = {showDialog = false},
            viewModel = viewModel
        )
    }
}

@Composable
fun FloatingActionBtn(
    onClick: () -> Unit,
    modifier: Modifier
){
     FloatingActionButton(
         onClick =  {
             onClick()
         }
     ){
         Icon(
             Icons.Filled.Add,
             "Floating Action Button"
         )
     }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialogFun(
    onDismiss: () -> Unit,
    viewModel: InterPackViewModel
){
    var inputName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
          onDismiss()
        },
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
               if (inputName.isNotBlank()) {
                   val newItem = InterPackages(titleOfInterPackages = inputName)
                   viewModel.addInterPackViewModel(newItem)
                   inputName = ""
                   onDismiss
               }
           }) {
               Text("Создать")
           }
        },
        dismissButton = {
            TextButton(onClick = {onDismiss()}) {
                Text("Закрыть")
            }
        }
    )
}
