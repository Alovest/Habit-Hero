package com.example.habithero.infrastructure.data.repository.homescreen.habitsActs

import com.example.habithero.domain.source.homescreen.habitsActs.DeleteUsersHabitRepository
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class DeleteUsersHabitImpl(
    override val userDao: UserDao
): DeleteUsersHabitRepository {
    override suspend fun deleteUsersHabit(user: User) {
        super.deleteUsersHabit(user)
    }
}