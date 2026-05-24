package com.example.habithero.domain.source.homescreen

import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

interface UpdateUsersHabit {
    val userDao: UserDao

    suspend fun updateUsersHabit(user: User){
        userDao.updateUser(user)
    }
}