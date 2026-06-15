package com.example.habithero.infrastructure.data.repository

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class TodoListRepositoryImpl(
    override val todoDao: TodoDao,
    override val IPid: Long
): TodoListRepository {
    override suspend fun addItemTodoList(IPid: TodoList) {
        super.addItemTodoList(IPid)
    }

    override val readAllTodo: LiveData<List<TodoList>> = todoDao.getAllTodo(IPid)
}