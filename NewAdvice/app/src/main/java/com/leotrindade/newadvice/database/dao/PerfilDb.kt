package com.leotrindade.newadvice.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leotrindade.newadvice.model.Perfil

@Database(entities = [Perfil::class], version = 1)
abstract class PerfilDb : RoomDatabase() {
    abstract fun perfilDao(): PerfilDao

    companion object {

        @Volatile
        private var instance: PerfilDb? = null

        fun getDatabase(context: Context): PerfilDb {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PerfilDb::class.java,
                    "perfil_db"
                )
                    .build()
                instance = newInstance
                newInstance
            }
        }
    }
}
