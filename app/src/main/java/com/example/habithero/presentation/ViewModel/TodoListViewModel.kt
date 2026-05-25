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

class TodoListViewModel(application: Application,
                        private val useCase: CreateTodoUseCase,
                        private var repository: TodoListRepository
): AndroidViewModel(application) {
    val readAllData: LiveData<List<TodoList>>
    private val allTodoList = MutableLiveData<List<TodoList>>()
    val usersTodoList: LiveData<List<TodoList>> = allTodoList
    init {
        val todoDao = TodoDatabase.getDatabaseToTodo(application).todoDao()
        repository = TodoListRepositoryImpl(todoDao)
        readAllData = repository.readAllTodo
        readAllData.observeForever { data ->
            allTodoList.value = data
        }
    }

    fun getTodoFromUser(todo: TodoList){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(todo)
            repository.readAllTodo.value?.let { updatedData ->
                allTodoList.postValue(updatedData)
            }
        }
    }
}