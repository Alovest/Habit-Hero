package com.example.habithero.di.domainModules

import com.example.habithero.domain.source.homescreen.counterHabit.CounterHabitRepository
import com.example.habithero.domain.source.homescreen.habitsActs.DeleteUsersHabitRepository
import com.example.habithero.domain.source.homescreen.habitsActs.UpdateUsersHabit
import com.example.habithero.domain.source.homescreen.habitsActs.UsersRepository
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.counterHabit.CounterHabitUsecase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.habitsActs.UpdateUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.repository.homescreen.counterHabit.CounterHabitRepositoryImpl
import com.example.habithero.infrastructure.data.repository.todoList.InterPackRepositoryImpl
import com.example.habithero.infrastructure.data.repository.todoList.TodoListRepositoryImpl
import com.example.habithero.infrastructure.data.repository.homescreen.habitsActs.DeleteUsersHabitImpl
import com.example.habithero.infrastructure.data.repository.homescreen.habitsActs.UpdateUsersHabitImpl
import com.example.habithero.infrastructure.data.repository.homescreen.habitsActs.UserRepositoryImpl
import org.koin.dsl.module

val domainModule = module{
    single<UsersRepository> {
        UserRepositoryImpl(get())
    }
    single<UpdateUsersHabit> {
        UpdateUsersHabitImpl(get())
    }
    single<DeleteUsersHabitRepository> {
        DeleteUsersHabitImpl(get())
    }
    // use cases
    factory<DeleteUsersHabitUseCase>{
        DeleteUsersHabitUseCase(get())
    }
    factory<FetchDataUserUseCase> {
        FetchDataUserUseCase(get())
    }
    factory<UpdateUsersHabitUseCase> {
        UpdateUsersHabitUseCase(get())
    }
    //TodoLists part
    //use case
    factory<CreateTodoUseCase> {
        CreateTodoUseCase(get())
    }
    //repository
    single<TodoListRepository> {
        TodoListRepositoryImpl(get())
    }
    //InterPackages
    //use case
    factory<InterPackAddUseCase> {
        InterPackAddUseCase(get())
    }
    //repository
    single<InterPackAddRepository> {
        InterPackRepositoryImpl(get())
    }
    single<CounterHabitRepository> {
        CounterHabitRepositoryImpl(get())
    }
    factory<CounterHabitUsecase> {
        CounterHabitUsecase(get())
    }
}
