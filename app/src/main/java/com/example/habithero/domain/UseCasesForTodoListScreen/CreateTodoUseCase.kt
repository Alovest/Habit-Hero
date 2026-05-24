package com.example.habithero.domain.UseCasesForTodoListScreen

import com.example.habithero.infrastructure.data.Repository.TodoListRepository
import com.example.habithero.infrastructure.data.Room.Data.TodoList

class CreateTodoUseCase(
    private val repository: TodoListRepository
) {
    suspend fun execute(todo: TodoList){
        repository.getTodoFromUserRep(todo)
    }
}