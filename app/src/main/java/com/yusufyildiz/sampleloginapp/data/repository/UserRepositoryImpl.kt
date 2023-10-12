package com.yusufyildiz.sampleloginapp.data.repository

import com.yusufyildiz.sampleloginapp.data.model.UserEntity
import com.yusufyildiz.sampleloginapp.data.source.local.UserDao
import com.yusufyildiz.sampleloginapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(userEntity: UserEntity) = userDao.insertUser(userEntity)
    override suspend fun getUsers() = userDao.getUsers()
    override suspend fun getUser(email: String, password: String) = userDao.getUser(email,password)
    override fun isUserLoggedIn(alreadyUserLoggedIn: Boolean): Boolean {
        return alreadyUserLoggedIn
    }
}