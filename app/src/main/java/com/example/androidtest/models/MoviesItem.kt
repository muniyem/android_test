package com.example.androidtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidtest.constants.Constants

@Entity
data class MoviesItem(
    var adult: Boolean,
    var backdrop_path: String,
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
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

}