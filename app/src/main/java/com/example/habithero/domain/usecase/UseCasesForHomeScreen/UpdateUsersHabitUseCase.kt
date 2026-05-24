package com.example.habithero.domain.usecase.UseCasesForHomeScreen

import com.example.habithero.domain.source.homescreen.UpdateUsersHabit
import com.example.habithero.infrastructure.data.Room.Data.User

class UpdateUsersHabitUseCase(private val repository: UpdateUsersHabit) {
    suspend fun execute(user: User){
        repository.updateUsersHabit(user)
    }
}