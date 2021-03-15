package com.example.voaenglish.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message_gmail_table")
data class MessageDB(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "message")
        val message: String? = null,
        @ColumnInfo(name = "subject")
        val subject: String? = null

)
