package com.example.habithero.domain.source.todolist

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

interface TodoListRepository {
    val todoDao: TodoDao
    val readAllTodo: LiveData<List<TodoList>>
    val IPid: Long
    suspend fun addItemTodoList(IPid: TodoList) {
        todoDao.getTodoList(IPid)
    }
}