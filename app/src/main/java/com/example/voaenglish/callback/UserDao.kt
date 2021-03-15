package com.example.voaenglish.callback

import androidx.room.*
import com.example.voaenglish.model.UserKotlin

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY first_name ASC")
    fun getAll(): List<UserKotlin>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: UserKotlin)

    @Delete
    fun delete(user: UserKotlin)

}