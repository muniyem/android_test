package com.example.androidtest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidtest.models.MoviesItem

@Dao
interface MovieDao {
    @Query("SELECT * FROM MoviesItem")
    fun getAllMovies(): List<MoviesItem>

    @Query("SELECT * FROM MoviesItem WHERE title LIKE '%' || :search_query || '%'")
    fun getFilteredMovies(search_query: String?): List<MoviesItem>

    @Insert
    fun insertAll(vararg movies: MoviesItem)

    @Delete
    fun delete(movieItem: MoviesItem)

    @Query("DELETE FROM MoviesItem")
    fun moviesItemTable()
}