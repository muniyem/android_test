package com.example.androidtest.repo

import com.example.androidtest.models.Movies
import retrofit2.Response
import javax.inject.Inject

open class MoviesRemoteRepository @Inject constructor(
    private val provideMovieService: MoviesAPI,
) {
    suspend fun getMoviesList(
        authToken: String?,
        language: String?,
        pageNo: Int?
    ): Response<Movies>? {
        return provideMovieService.getMovies(authToken, language, pageNo)
    }

}
