package com.yusufyildiz.sampleloginapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusufyildiz.sampleloginapp.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class UsersRoomDB : RoomDatabase() {
    abstract fun usersDao(): UserDao
}