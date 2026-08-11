package com.decloudius.jmodupe

import com.decloudius.jmodupe.data.local.db.UserDao
import com.decloudius.jmodupe.data.local.entity.UserEntity
import com.decloudius.jmodupe.domain.model.User
import com.decloudius.jmodupe.data.repository.UserRepositoryImpl
import com.decloudius.jmodupe.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit test for [UserRepositoryImpl].
 */
class UserRepositoryImplTest {

    private lateinit var fakeDao: FakeUserDao
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setUp() {
        fakeDao = FakeUserDao()
        repository = UserRepositoryImpl(fakeDao)
    }

    @After
    fun tearDown() {
        // No cleanup needed
    }

    @Test
    fun `insertUser maps User to UserEntity and calls DAO`() = runBlocking {
        val user = User(
            id = 1,
            email = "test@example.com",
            password = "secret",
            name = "Test User",
            ktp = "1234567890",
            phone = "0812345678"
        )

        repository.insertUser(user)

        assertNotNull(fakeDao.insertedUser)
        assertEquals(user.email, fakeDao.insertedUser?.email)
        assertEquals(user.password, fakeDao.insertedUser?.password)
        assertEquals(user.name, fakeDao.insertedUser?.name)
        assertEquals(user.ktp, fakeDao.insertedUser?.ktp)
        assertEquals(user.phone, fakeDao.insertedUser?.phone)
    }

    @Test
    fun `getUser returns User when DAO returns UserEntity`() = runBlocking {
        val testEntity = UserEntity(
            id = 2,
            email = "dao@example.com",
            password = "daoPass",
            name = "DAO User",
            ktp = "9876543210",
            phone = "0898765432"
        )
        fakeDao.getUserResult = testEntity

        val result = repository.getUser()

        assertNotNull(result)
        assertEquals(testEntity.email, result?.email)
        assertEquals(testEntity.password, result?.password)
        assertEquals(testEntity.name, result?.name)
        assertEquals(testEntity.ktp, result?.ktp)
        assertEquals(testEntity.phone, result?.phone)
    }

    @Test
    fun `getUser returns null when DAO returns null`() = runBlocking {
        fakeDao.getUserResult = null
        val result = repository.getUser()
        assertNull(result)
    }

    @Test
    fun `deleteUser calls DAO deleteUser`() = runBlocking {
        repository.deleteUser()
        assertTrue(fakeDao.deleteCalled)
    }

    /**
     * Fake DAO for testing.
     */
    private class FakeUserDao : UserDao {
        var insertedUser: UserEntity? = null
        var getUserResult: UserEntity? = null
        var deleteCalled = false

        override suspend fun insertUser(user: UserEntity) {
            insertedUser = user
        }

        override suspend fun getUser(): UserEntity? {
            return getUserResult
        }

        override suspend fun deleteUser() {
            deleteCalled = true
        }
    }
}