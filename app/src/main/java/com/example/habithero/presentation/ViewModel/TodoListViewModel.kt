package com.example.habithero.presentation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.infrastructure.data.repository.TodoListRepositoryImpl
import com.example.habithero.infrastructure.data.Room.Database.TodoDatabase
import com.example.habithero.infrastructure.data.Room.Data.TodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class TodoListViewModel(application: Application,
//                        private val useCase: CreateTodoUseCase,
//                        private var repository: TodoListRepository,
//                        val IPid: Long
//): AndroidViewModel(application) {
//    val readAllData: LiveData<List<TodoList>>
//    private val allTodoList = MutableLiveData<List<TodoList>>()
//    val usersTodoList: LiveData<List<TodoList>> = allTodoList
//    init {
//        val todoDao = TodoDatabase.getDatabaseToTodo(application).todoDao()
//        repository = TodoListRepositoryImpl(todoDao, IPid)
//        readAllData = repository.readAllTodo
//        readAllData.observeForever { data ->
//            allTodoList.value = data
//        }
//    }
//
//    fun getTodoFromUser(todo: TodoList){
//        viewModelScope.launch(Dispatchers.IO) {
//            useCase.execute(todo)
//            repository.readAllTodo.value?.let { updatedData ->
//                allTodoList.postValue(updatedData)
//            }
//        }
//    }
//}

class TodoListViewModel(
    application: Application,
    private val useCase: CreateTodoUseCase,
    private val repository: TodoListRepository,
    val IPid: Long? // Strictly Nullable Long? to match room schema
): AndroidViewModel(application) {

    private val allTodoList = MutableLiveData<List<TodoList>>()
    val usersTodoList: LiveData<List<TodoList>> = allTodoList

    // 1. Automatically binds to the correct SQL data stream on startup
    val readAllData: LiveData<List<TodoList>> = if (IPid == 0L || IPid == null) {
        repository.getAllTodo(null) // Queries where IPid IS NULL
    } else {
        repository.getAllTodo(IPid)  // Queries the explicit folder package
    }

    init {
        // Observe changes safely to pipe into the auxiliary usersTodoList state stream
        readAllData.observeForever { data ->
            allTodoList.value = data ?: emptyList()
        }
    }

    fun getTodoFromUser(todo: TodoList) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(todo)
        }
    }
}
