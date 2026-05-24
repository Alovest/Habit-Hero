package com.example.habithero.infrastructure.data.repository.homescreen

import com.example.habithero.domain.source.homescreen.UpdateUsersHabit
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UpdateUsersHabitImpl(override val userDao: UserDao): UpdateUsersHabit {
    override suspend fun updateUsersHabit(user: User) {
        super.updateUsersHabit(user)
    }
}