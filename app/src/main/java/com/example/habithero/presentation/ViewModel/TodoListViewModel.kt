package com.example.habithero.presentation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.infrastructure.data.Room.Data.TodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(
    application: Application,
    private val useCase: CreateTodoUseCase,
    private val repository: TodoListRepository,
    val IPid: Long?
): AndroidViewModel(application) {

    private val allTodoList = MutableLiveData<List<TodoList>>()
    val usersTodoList: LiveData<List<TodoList>> = allTodoList

    val readAllData: LiveData<List<TodoList>> = if (IPid == 0L || IPid == null) {
        repository.getAllTodo(null)
    } else {
        repository.getAllTodo(IPid)
    }

    init {
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
