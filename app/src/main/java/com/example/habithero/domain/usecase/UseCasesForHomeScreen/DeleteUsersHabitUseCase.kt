package com.example.habithero.domain.usecase.UseCasesForHomeScreen

import com.example.habithero.domain.source.homescreen.DeleteUsersHabitRepository
import com.example.habithero.infrastructure.data.Room.Data.User

class DeleteUsersHabitUseCase(
    private val repository: DeleteUsersHabitRepository
) {
    suspend fun execute(user: User) {
        repository.deleteUsersHabit(user)
    }
}