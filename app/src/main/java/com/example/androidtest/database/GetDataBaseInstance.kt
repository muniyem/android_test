package com.example.androidtest.database

import android.content.Context
import androidx.room.Room.databaseBuilder

object GetDataBaseInstance {

    private var myDatabase: AppDatabase? = null
//    private fun GetDataBaseInstance() {}

    fun getRoomDataBase(context: Context?): AppDatabase? {
        if (myDatabase == null) {
            myDatabase = databaseBuilder(context!!, AppDatabase::class.java, "AndroidTest")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build()
        }
        return myDatabase
    }
}