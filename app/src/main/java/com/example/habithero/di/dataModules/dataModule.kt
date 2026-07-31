package com.example.habithero.di.dataModules

import com.example.habithero.domain.source.homescreen.counterHabit.CounterHabitRepository
import com.example.habithero.domain.source.homescreen.habitsActs.UsersRepository
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.counterHabit.CounterHabitUsecase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.UpdateUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.Room.Database.TodoDatabase
import com.example.habithero.presentation.ViewModel.CounterHabitViewModel
import com.example.habithero.presentation.ViewModel.InterPackViewModel
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.presentation.ViewModel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    single {
        TodoDatabase.getDatabaseToTodo(androidApplication()).todoDao()
    }
    single {
        TodoDatabase.getDatabaseToTodo(androidApplication()).interPackDao()
    }
    single {
        TodoDatabase.getDatabaseToTodo(androidApplication()).userDao()
    }

    single {
        TodoDatabase.getDatabaseToTodo(androidApplication()).habitCounterDao()
    }
        //viewModel
    viewModel{
        TodoListViewModel(androidApplication(),
        get<CreateTodoUseCase>(),
        get<TodoListRepository>(),
            it.get()
        )
    }

    viewModel {
        UserViewModel(androidApplication(),
            get<FetchDataUserUseCase>(),
            get<DeleteUsersHabitUseCase>(),
            get<UpdateUsersHabitUseCase>(),
            get<UsersRepository>()
        ) }

    viewModel{(folderId: Long) ->
        InterPackViewModel(androidApplication(),
            get<InterPackAddRepository>(),
            get<InterPackAddUseCase>(),
            folderId
        )
    }


    viewModel{
        CounterHabitViewModel(
            get<CounterHabitUsecase>(),
            get<CounterHabitRepository>()
        )
    }
}