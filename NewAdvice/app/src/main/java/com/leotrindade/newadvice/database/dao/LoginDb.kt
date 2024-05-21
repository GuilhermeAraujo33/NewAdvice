package com.leotrindade.newadvice.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leotrindade.newadvice.model.Login

@Database(entities = [Login::class], version = 1)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): LoginDao

    companion object {

        private lateinit var instance: LoginDb

        fun getDatabase(context: Context): LoginDb {
            if (!Companion::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        LoginDb::class.java,
                        "login_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
