package com.yusufyildiz.sampleloginapp.di

import com.yusufyildiz.sampleloginapp.data.repository.UserRepositoryImpl
import com.yusufyildiz.sampleloginapp.data.source.local.UserDao
import com.yusufyildiz.sampleloginapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userDao)

}