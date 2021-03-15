package com.example.voaenglish.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.voaenglish.callback.MessageDao
import com.example.voaenglish.callback.UserDao
import com.example.voaenglish.model.Message
import com.example.voaenglish.model.MessageDB
import com.example.voaenglish.model.UserKotlin
import kotlinx.coroutines.CoroutineScope

@Database(entities = [UserKotlin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "test_db")
                    .addCallback(
                            object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                }
                            }
                    ).build()
        }
    }
}