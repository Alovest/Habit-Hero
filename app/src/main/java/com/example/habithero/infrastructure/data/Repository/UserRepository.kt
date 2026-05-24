package com.example.habithero.infrastructure.data.Repository

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UserRepository(
    private val userDao: UserDao
) {
    val readAllHabit: LiveData<List<User>> = userDao.getAllHabits()

    suspend fun getHabitFromUserRep(user: User){
        userDao.getHabitUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUserRepo(user: User): Int{
      return  userDao.deleteUser(user)
    }

}