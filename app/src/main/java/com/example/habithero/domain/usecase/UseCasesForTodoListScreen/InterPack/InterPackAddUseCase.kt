package com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack

import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackAddUseCase(private val repository: InterPackAddRepository) {
    suspend fun execute(item: InterPackages){
        if (item.folderId <= 0) {
            android.util.Log.e("USE_CASE_TRACE", "Blocked UseCase step for '${item.titleOfInterPackages}' due to folderId: ${item.folderId}")
            return
        }
        repository.addInterPackItem(item)
    }
}