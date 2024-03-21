package com.example.androidtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
//import com.example.androidtest.database.DataConverters


data class Movies(
    val page: Int,
    val results: List<MoviesItem>,
    val total_pages: Int,
    val total_results: Int
){

}