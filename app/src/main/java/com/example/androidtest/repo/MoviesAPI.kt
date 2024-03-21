package com.example.androidtest.repo

import com.example.androidtest.constants.Constants
import com.example.androidtest.models.MovieDetail
import com.example.androidtest.models.Movies
import retrofit2.Response
import retrofit2.http.*

interface MoviesAPI {
    @GET("popular")
    suspend fun getMovies(
        @Header(Constants.HEADER) header: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<Movies>?

    suspend  @GET("{movie_id}")
     fun getMovieDetail(
        @Header(Constants.HEADER) header: String?,
        @Path("movie_id") movieId: Int?,
        @Query("language") language: String?
    ): Response<MovieDetail>?


}
