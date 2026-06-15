package com.example.habithero.di.domainModules

import com.example.habithero.domain.source.homescreen.DeleteUsersHabitRepository
import com.example.habithero.domain.source.homescreen.UpdateUsersHabit
import com.example.habithero.domain.source.homescreen.UsersRepository
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.UpdateUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.repository.InterPackRepositoryImpl
import com.example.habithero.infrastructure.data.repository.TodoListRepositoryImpl
import com.example.habithero.infrastructure.data.repository.homescreen.DeleteUsersHabitImpl
import com.example.habithero.infrastructure.data.repository.homescreen.UpdateUsersHabitImpl
import com.example.habithero.infrastructure.data.repository.homescreen.UserRepositoryImpl
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
        TodoListRepositoryImpl(get(), get())
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
}
