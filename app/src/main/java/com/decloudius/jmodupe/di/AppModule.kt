package com.decloudius.jmodupe.di

import androidx.room.Room
import com.decloudius.jmodupe.data.local.db.AppDatabase
import com.decloudius.jmodupe.data.repository.UserRepositoryImpl
import com.decloudius.jmodupe.domain.repository.UserRepository
import com.decloudius.jmodupe.presentation.screens.beranda.BerandaViewModel
import com.decloudius.jmodupe.presentation.screens.login.LoginViewModel
import com.decloudius.jmodupe.presentation.screens.profil.ProfilViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "jmo_database"
        ).build()
    }

    single { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { BerandaViewModel(get()) }
    viewModel { ProfilViewModel(get()) }
}
