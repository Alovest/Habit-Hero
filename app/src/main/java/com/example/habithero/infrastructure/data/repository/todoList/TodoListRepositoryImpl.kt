package com.example.habithero.infrastructure.data.repository.todoList

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class TodoListRepositoryImpl(
    override val todoDao: TodoDao
) : TodoListRepository {

    override suspend fun addItemTodoList(todo: TodoList) {
        todoDao.getTodoList(todo)
    }

    override val readAllTodo: LiveData<List<TodoList>> = todoDao.getAllTodo()

    override fun getAllTodo(ipid: Long?): LiveData<List<TodoList>> {
        return todoDao.getAllTodo()
    }
}