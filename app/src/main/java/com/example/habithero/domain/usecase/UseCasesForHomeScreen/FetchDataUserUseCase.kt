package com.example.habithero.domain.usecase.UseCasesForHomeScreen

import com.example.habithero.domain.source.homescreen.UsersRepository
import com.example.habithero.infrastructure.data.Room.Data.User

class FetchDataUserUseCase(
    private val repository: UsersRepository
) {
    suspend fun execute(user: User){
        repository.fetchDataUser(user)
    }
}