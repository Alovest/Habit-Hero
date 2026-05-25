package com.example.habithero.infrastructure.data.repository.homescreen

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.homescreen.UsersRepository
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UserRepositoryImpl(override val userDao: UserDao) : UsersRepository {
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

    override val readAllHabit: LiveData<List<User>> = userDao.getAllHabits()

     override suspend fun fetchDataUser(user: User) {
         super.fetchDataUser(user)
     }
}