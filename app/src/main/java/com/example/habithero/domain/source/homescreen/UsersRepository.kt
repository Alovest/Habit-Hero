package com.example.habithero.domain.source.homescreen

import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

interface UsersRepository {
    val userDao: UserDao

    suspend fun fetchDataUser(user: User){
        userDao.getHabitUser(user)
    }
}