package com.example.habithero.presentation.Navigation.NavGraph

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomSheet(viewModel: UserViewModel = viewModel()) {
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showSheet by remember { mutableStateOf(false) }
//    var inputName by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//
//    fun Notification(){
//        val notificationWork: WorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
//            .setInitialDelay(10, TimeUnit.SECONDS)
//            .build()
//        WorkManager.getInstance(context).enqueue(notificationWork)
//    }
//
//
//    if (showSheet) {
//        ModalBottomSheet(onDismissRequest = {showSheet = false}, sheetState = sheetState){
//            Column(modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()) {
//                Text(text = "Введите привычку", style = MaterialTheme.typography.h2)
//                Spacer(modifier = Modifier.padding(8.dp))
//                TextField(
//                    value = inputName,
//                    onValueChange = { inputName = it },
//                    singleLine = true,
//                    placeholder = { Text("Название привычки") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Row(modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End) {
//                    TextButton(onClick = {
//                        showSheet = false
//                        inputName = ""
//                    }) {
//                        Text(text = "Отмена")
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(onClick = {
//                        if (inputName.isNotBlank()) {
//                            val newUser = User(title = inputName)
//                            viewModel.getHabitFromUser(newUser)
//                            inputName = ""
//                            showSheet = false
//
//                            Notification()
//                        }
//                    }) {
//                        Text(text = "Добавить")
//                    }
//                }
//            }
//        }
//    }
//}

