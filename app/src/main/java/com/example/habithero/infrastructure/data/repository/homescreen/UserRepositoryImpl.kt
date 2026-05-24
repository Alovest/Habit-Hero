package com.example.habithero.infrastructure.data.repository.homescreen

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.homescreen.UsersRepository
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UserRepositoryImpl(override val userDao: UserDao) : UsersRepository
    //private val userDao: UserDao
 {
    val readAllHabit: LiveData<List<User>> = userDao.getAllHabits()
//
//    suspend fun getHabitFromUserRep(user: User){
//        userDao.getHabitUser(user)
//    }
//
//    suspend fun updateUser(user: User){
//        userDao.updateUser(user)
//    }
//
//    suspend fun deleteUserRepo(user: User): Int{
//      return  userDao.deleteUser(user)
//    }
     override suspend fun fetchDataUser(user: User) {
         super.fetchDataUser(user)
     }
}