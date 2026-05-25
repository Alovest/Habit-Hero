package com.example.habithero.domain.source.homescreen

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

interface UsersRepository {
    val userDao: UserDao
    val readAllHabit: LiveData<List<User>>
    suspend fun fetchDataUser(user: User){
        userDao.getHabitUser(user)
    }
}