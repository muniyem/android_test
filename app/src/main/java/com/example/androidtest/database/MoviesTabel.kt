package com.example.androidtest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidtest.constants.Constants
//import com.example.androidtest.database.DataConverters

@Entity
data class MoviesTable(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
){
    val getImageURL: String
        get() = Constants.IMAGE_BASE_URL+poster_path

    @PrimaryKey(autoGenerate = true)
    private var primaryKey = 0

    fun getPrimaryKey(): Int {
        return primaryKey
    }

    fun setPrimaryKey(primaryKey: Int) {
        this.primaryKey = primaryKey
    }

//    @ColumnInfo(name = "results")
//    private val dbResultsMovie: List<MoviesItem>? = null
}