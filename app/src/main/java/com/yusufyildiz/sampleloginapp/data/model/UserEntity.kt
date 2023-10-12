package com.yusufyildiz.sampleloginapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?=null,

    @ColumnInfo(name = "first_name")
    val firstName: String?=null,

    @ColumnInfo(name = "last_name")
    val lastName: String?=null,

    @ColumnInfo(name = "email")
    val email: String?=null,

    @ColumnInfo(name = "password")
    val password: String?=null
)