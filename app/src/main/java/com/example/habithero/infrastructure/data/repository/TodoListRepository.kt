package com.example.habithero.infrastructure.data.repository

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class TodoListRepository(
    private val todoDao: TodoDao
) {
    val readAllTodo: LiveData<List<TodoList>> = todoDao.getAllTodo()

    suspend fun getTodoFromUserRep(todo: TodoList){
        todoDao.getTodoList(todo)
    }
}