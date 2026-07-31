package com.example.habithero.domain.source.homescreen.counterHabit

import kotlinx.coroutines.flow.Flow

interface CounterHabitRepository {
    fun insertCount(): Flow<Int>
    suspend fun incrementCounter()
}