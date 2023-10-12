package com.yusufyildiz.sampleloginapp.di

import android.content.Context
import androidx.room.Room
import com.yusufyildiz.sampleloginapp.data.source.local.UserDao
import com.yusufyildiz.sampleloginapp.data.source.local.UsersRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideUsersRoomDB(
        @ApplicationContext context: Context
    ): UsersRoomDB = Room.databaseBuilder(
        context,
        UsersRoomDB::class.java,
        "users_database",
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUsersDAO(
        usersRoomDB: UsersRoomDB
    ): UserDao = usersRoomDB.usersDao()
}