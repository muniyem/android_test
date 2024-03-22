package com.example.androidtest.repo

import com.example.androidtest.models.MovieDetail
import com.example.androidtest.models.Movies
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject
@ActivityRetainedScoped
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

    suspend fun movieDetail(
        authToken: String?,
        movieId: Int?,
        language: String?
    ): Response<MovieDetail>? {
        return provideMovieService.getMovieDetail(authToken,movieId, language)
    }
}
