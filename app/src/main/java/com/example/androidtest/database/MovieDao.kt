package com.example.androidtest.database

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidtest.models.MoviesItem

@Dao
interface MovieDao {
    @Query("SELECT * FROM MoviesTable")
    fun getAllMovies(): List<MoviesTable>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<MoviesItem>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): MoviesItem

    @Insert
    fun insertAll(vararg movies: MoviesTable)

    @Delete
    fun delete(movieItem: MoviesTable)
}