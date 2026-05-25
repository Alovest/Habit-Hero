package com.example.habithero.domain.source.todolist

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

interface TodoListRepository {
    val todoDao: TodoDao
    val readAllTodo: LiveData<List<TodoList>>
    suspend fun addItemTodoList(todo: TodoList) {
        todoDao.getTodoList(todo)
    }
}