package com.example.habithero.domain.usecase.UseCasesForHomeScreen.counterHabit

import com.example.habithero.domain.source.homescreen.counterHabit.CounterHabitRepository

class CounterHabitUsecase(private val repository: CounterHabitRepository) {
    suspend operator fun invoke(){
        repository.incrementCounter()
    }
}