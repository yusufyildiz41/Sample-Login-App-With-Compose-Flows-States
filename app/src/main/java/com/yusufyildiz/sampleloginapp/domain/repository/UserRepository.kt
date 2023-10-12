package com.yusufyildiz.sampleloginapp.domain.repository

import com.yusufyildiz.sampleloginapp.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(userEntity: UserEntity)
    suspend fun getUsers(): Flow<List<UserEntity>>
    suspend fun getUser(email: String,password: String): Flow<UserEntity>
    fun isUserLoggedIn(alreadyUserLoggedIn: Boolean = false): Boolean
}