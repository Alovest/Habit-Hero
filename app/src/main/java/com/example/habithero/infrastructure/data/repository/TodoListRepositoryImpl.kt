package com.example.habithero.infrastructure.data.repository

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class TodoListRepositoryImpl(
    override val todoDao: TodoDao
    // Removed IPid since folders are root elements
) : TodoListRepository {

    // FIXED: Calls your exact DAO insert function name
    override suspend fun addItemTodoList(todo: TodoList) {
        todoDao.getTodoList(todo)
    }

    // FIXED: Calls your exact DAO select query name
    override val readAllTodo: LiveData<List<TodoList>> = todoDao.getAllTodo()

    // Optional interface override matching your old structure
    override fun getAllTodo(ipid: Long?): LiveData<List<TodoList>> {
        return todoDao.getAllTodo()
    }
}
