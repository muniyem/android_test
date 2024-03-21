package com.example.androidtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidtest.constants.Constants
import com.example.androidtest.database.DataConverters

@Entity
data class MoviesItem(
    var adult: Boolean,
    var backdrop_path: String,
//    @TypeConverters(DataConverters::class)
//    val genre_ids: List<Int>,
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
    constructor() : this( false,"",0,"","","",0.0,"","","",false,0.0,0)
    constructor(
        adult: Boolean,
        backdropPath: String,
        id: Int,
        originalLanguage: String,
        originalTitle: String,
        overview: String,
        popularity: Double,
        posterPath: String,
        releaseDate: String,
        title: String
    ) : this(

    )

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