package com.example.voaenglish.viewmodel

import androidx.annotation.WorkerThread
import com.example.voaenglish.callback.UserDao
import com.example.voaenglish.model.UserKotlin
import java.util.concurrent.Flow

class WordRepository(private val userDao: UserDao) {

    private object Holder {

    }

    val allUser = userDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userKotlin: UserKotlin) {
        userDao.insertAll(userKotlin)
    }
}