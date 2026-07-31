package com.example.habithero.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.source.homescreen.counterHabit.CounterHabitRepository
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.counterHabit.CounterHabitUsecase
import com.example.habithero.infrastructure.data.Room.Data.HabitCounter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CounterHabitViewModel(private val usecase: CounterHabitUsecase, private val repository: CounterHabitRepository): ViewModel() {
    val counterValue: StateFlow<Int> = repository.insertCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 1
        )

    fun counter(){
        viewModelScope.launch(Dispatchers.IO) {
            usecase()
        }
    }
}