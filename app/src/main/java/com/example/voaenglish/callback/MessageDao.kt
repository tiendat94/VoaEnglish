package com.example.voaenglish.callback

import androidx.room.*
import com.example.voaenglish.model.Message
import com.example.voaenglish.model.MessageDB


@Dao
interface MessageDao {
    @Query("SELECT * FROM message_gmail_table ORDER BY message ASC")
    fun getAll(): List<MessageDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg messageDB: List<MessageDB>)

    @Delete
    fun delete(messageDB: MessageDB)
}