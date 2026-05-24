package com.example.habithero.domain.UseCasesForHomeScreen.InterPack

import com.example.habithero.infrastructure.data.Repository.InterPackRepository
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackAddUseCase(private val repository: InterPackRepository) {
    suspend fun execute(item: InterPackages){
        repository.getInterPacksItems(item)
    }
}