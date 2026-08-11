package com.decloudius.jmodupe.data.repository

import com.decloudius.jmodupe.data.local.db.UserDao
import com.decloudius.jmodupe.data.local.entity.UserEntity
import com.decloudius.jmodupe.domain.model.User
import com.decloudius.jmodupe.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(
            UserEntity(
                email = user.email,
                password = user.password,
                name = user.name,
                ktp = user.ktp,
                phone = user.phone
            )
        )
    }

    override suspend fun getUser(): User? {
        return userDao.getUser()?.let {
            User(
                id = it.id,
                email = it.email,
                password = it.password,
                name = it.name,
                ktp = it.ktp,
                phone = it.phone
            )
        }
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}
