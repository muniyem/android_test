package com.example.androidtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtest.models.Movies
import com.example.androidtest.models.MoviesItem

@Database(entities = [MoviesTable::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}