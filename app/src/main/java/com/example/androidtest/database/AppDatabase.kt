package com.example.androidtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtest.models.MoviesItem

@Database(entities = [MoviesItem::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}