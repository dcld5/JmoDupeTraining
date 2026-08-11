package com.decloudius.jmodupe.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.decloudius.jmodupe.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("DELETE FROM users")
    suspend fun deleteUser()
}
