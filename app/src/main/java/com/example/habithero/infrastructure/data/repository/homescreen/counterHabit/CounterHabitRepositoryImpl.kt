package com.example.habithero.infrastructure.data.repository.homescreen.counterHabit

import com.example.habithero.domain.source.homescreen.counterHabit.CounterHabitRepository
import com.example.habithero.infrastructure.data.Room.Dao.HabitCounterDao
import com.example.habithero.infrastructure.data.Room.Data.HabitCounter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transform

class CounterHabitRepositoryImpl(val habitCounterDao: HabitCounterDao): CounterHabitRepository {

    override fun insertCount(): Flow<Int> {
        return habitCounterDao.getCounterValue().transform { entity ->
            emit(entity?.value ?: 0)
        }
    }

    override suspend fun incrementCounter() {
        val currentCounter = habitCounterDao.getCounterValue().firstOrNull()?.value ?: 0
        val newCount = currentCounter + 1
        habitCounterDao.increment(HabitCounter(value = newCount))
    }
}