package com.example.habithero.presentation.Navigation.NavGraph

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.habithero.infrastructure.WorkManager.NotifyWorker
import com.example.habithero.infrastructure.data.Room.Data.User
import com.example.habithero.presentation.MainScreens.CartScreen.CartScreen
import com.example.habithero.presentation.MainScreens.HomeScreen.HomeScreen
import com.example.habithero.presentation.MainScreens.PomodoroScreen.Pomodoro
import com.example.habithero.presentation.MainScreens.TodoListScreen.TodoListOfPackage
import com.example.habithero.presentation.MainScreens.TodoListScreen.TodoListScreen
import com.example.habithero.presentation.Navigation.BottomNavigation.NavBarWithCenterButton
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.presentation.ViewModel.UserViewModel
import com.example.habithero.ui.theme.PurpleOfScreen
import org.koin.androidx.compose.koinViewModel
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val viewModel: UserViewModel = koinViewModel()
    val navController = rememberNavController()
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var inputName by remember { mutableStateOf("") }
    val context = LocalContext.current
    fun Notification(){
        val notificationWork: WorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(context).enqueue(notificationWork)
    }


    if (showSheet) {
        ModalBottomSheet(onDismissRequest = {showSheet = false}, sheetState = sheetState){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(text = "Создайте привычку", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    singleLine = true,
                    placeholder = { Text("Название привычки") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = {
                        showSheet = false
                        inputName = ""
                    }) {
                        Text(text = "Отмена")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (inputName.isNotBlank()) {
                            val newUser = User(title = inputName)
                            viewModel.getHabitFromUser(newUser)
                            inputName = ""
                            showSheet = false

                            Notification()
                        }
                    }) {
                        Text(text = "Добавить")
                    }
                }
            }
        }
    }


    Scaffold(
        bottomBar = { NavBarWithCenterButton(navController, onCenterButtonClick = {
            showSheet  = true
        })}
    ) {
        innerPadding ->
        Box(Modifier.padding(innerPadding).background(PurpleOfScreen)) {
            val TodoViewModel: TodoListViewModel = koinViewModel()
            NavHost(navController, startDestination = "Home") {
                composable("Home") {HomeScreen()}
                composable("TodoList") { TodoListScreen(TodoViewModel, navController) }
                composable("Pomodoro") { Pomodoro() }
                composable("Cart") { CartScreen() }
                composable(
                    route = "details/{titleOfTodo}",
                    arguments = listOf(
                        navArgument("titleOfTodo"){
                    type = NavType.StringType
                })) {backStackEntry ->
                    val userDataVal = backStackEntry.arguments?.getString("titleOfTodo")
                    val todoList by TodoViewModel.usersTodoList.observeAsState(emptyList())
                    val usersTodo = todoList.find { it.titleOfTodo == userDataVal }
                    if(usersTodo != null){
                        TodoListOfPackage(usersTodo, navController)
                    } else {
                        Log.d("Mistake of provide data", "${todoList.size}")
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(viewModel: UserViewModel = viewModel()) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    var inputName by remember { mutableStateOf("") }
    val context = LocalContext.current


    fun Notification(){
        val notificationWork: WorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(context).enqueue(notificationWork)
    }


    if (showSheet) {
        ModalBottomSheet(onDismissRequest = {showSheet = false}, sheetState = sheetState){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(text = "Введите привычку", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    singleLine = true,
                    placeholder = { Text("Название привычки") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = {
                        showSheet = false
                        inputName = ""
                    }) {
                        Text(text = "Отмена")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (inputName.isNotBlank()) {
                            val newUser = User(title = inputName)
                            viewModel.getHabitFromUser(newUser)
                            inputName = ""
                            showSheet = false

                            Notification()
                        }
                    }) {
                        Text(text = "Добавить")
                    }
                }
            }
        }
    }
}