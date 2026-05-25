package com.example.habithero.domain.usecase.UseCasesForTodoListScreen

import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.infrastructure.data.repository.TodoListRepositoryImpl
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class CreateTodoUseCase(
    private val repository: TodoListRepository
) {
    suspend fun execute(todo: TodoList){
        repository.addItemTodoList(todo)
    }
}