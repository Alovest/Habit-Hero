package com.example.habithero.domain.UseCasesForHomeScreen

import com.example.habithero.infrastructure.data.Repository.UserRepository
import com.example.habithero.infrastructure.data.Room.Data.User

class DeleteUsersHabit(
    val repository: UserRepository
) {
    suspend fun execute(user: User) {
        repository.deleteUserRepo(user)
    }
}