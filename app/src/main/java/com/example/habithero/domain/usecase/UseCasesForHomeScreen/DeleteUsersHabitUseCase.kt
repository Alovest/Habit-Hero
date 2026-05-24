package com.example.habithero.domain.usecase.UseCasesForHomeScreen

import com.example.habithero.infrastructure.data.Room.Data.User
import com.example.habithero.infrastructure.data.repository.homescreen.DeleteUsersHabitImpl

class DeleteUsersHabitUseCase(
    val repository: DeleteUsersHabitImpl
) {
    suspend fun execute(user: User) {
        repository.deleteUsersHabit(user)
    }
}