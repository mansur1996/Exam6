package com.example.exam6.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exam6.database.CardDB
import com.example.ogabekbasepractise.database.CardDao

@Database(entities = [CardDB::class], version = 1)

abstract class RoomManager : RoomDatabase() {


    abstract fun cardDao(): CardDao

    companion object {
        private var INSTANCE: RoomManager? = null

        fun getDatabase(context: Context): RoomManager? {
            if (INSTANCE == null) {
                synchronized(RoomManager::class.java) {
                    INSTANCE = Room.databaseBuilder(context, RoomManager::class.java, "cards.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}