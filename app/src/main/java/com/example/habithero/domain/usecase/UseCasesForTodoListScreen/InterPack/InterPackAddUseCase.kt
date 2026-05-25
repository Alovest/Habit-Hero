package com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack

import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackAddUseCase(private val repository: InterPackAddRepository) {
    suspend fun execute(item: InterPackages){
        repository.addInterPackItem(item)
    }
}