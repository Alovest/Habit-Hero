package com.example.habithero.infrastructure.data.repository.homescreen.habitsActs

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.homescreen.habitsActs.UsersRepository
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.User

class UserRepositoryImpl(override val userDao: UserDao) : UsersRepository {

    override val readAllHabit: LiveData<List<User>> = userDao.getAllHabits()

     override suspend fun fetchDataUser(user: User) {
         super.fetchDataUser(user)
     }
}