package com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs

import com.example.habithero.domain.source.homescreen.habitsActs.UpdateUsersHabit
import com.example.habithero.infrastructure.data.Room.Data.User

class UpdateUsersHabitUseCase(
    private val repository: UpdateUsersHabit
) {
    suspend fun execute(user: User){
        repository.updateUsersHabit(user)
    }
}