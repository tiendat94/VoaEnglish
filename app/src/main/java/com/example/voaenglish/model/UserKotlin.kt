package com.example.voaenglish.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserKotlin(@PrimaryKey @ColumnInfo(name = "id") val uid: Int, @ColumnInfo(name = "first_name") val firstName: String?, @ColumnInfo(name = "last_name") val lastName: String?)