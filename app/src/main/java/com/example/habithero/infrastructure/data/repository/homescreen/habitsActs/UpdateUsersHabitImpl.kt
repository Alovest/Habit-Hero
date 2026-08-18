package com.example.habithero.infrastructure.data.repository.homescreen.habitsActs

import com.example.habithero.domain.source.homescreen.habitsActs.UpdateUsersHabit
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UpdateUsersHabitImpl(override val userDao: UserDao): UpdateUsersHabit {
    override suspend fun updateUsersHabit(user: User) {
        userDao.updateUser(user)
    }
}