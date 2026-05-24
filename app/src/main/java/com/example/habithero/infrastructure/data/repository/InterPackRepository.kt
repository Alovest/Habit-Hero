package com.example.habithero.infrastructure.data.repository

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackRepository(private val interPackDao: InterPackDao) {

    val readAllDataInterPackRepository: LiveData<List<InterPackages>> = interPackDao.getAllItems()
    suspend fun getInterPacksItems(item: InterPackages){
        interPackDao.getInterPacksItem(item)
    }
}