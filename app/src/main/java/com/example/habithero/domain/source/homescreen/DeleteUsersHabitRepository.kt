package com.example.habithero.domain.source.homescreen

import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

interface DeleteUsersHabitRepository {
    val userDao: UserDao

    suspend fun deleteUsersHabit(user: User){
        userDao.deleteUser(user)
    }
}