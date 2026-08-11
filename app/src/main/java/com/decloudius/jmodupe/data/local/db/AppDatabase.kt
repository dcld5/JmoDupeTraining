package com.decloudius.jmodupe.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decloudius.jmodupe.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
