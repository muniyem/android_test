package com.example.androidtest.repo

import com.example.androidtest.helpers.BaseApiResponse
import com.example.androidtest.helpers.ResponseResult
import com.example.androidtest.helpers.applyCommonSideEffects
import com.example.androidtest.models.MovieDetail
import com.example.androidtest.models.Movies
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
@ActivityRetainedScoped
open class MoviesRemoteRepository @Inject constructor(
    private val provideMovieService: MoviesAPI,
): BaseApiResponse() {
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
//    suspend fun movieDetail(
//        authToken: String?,
//        id: Int,
//        language: String
//    ): Flow<ResponseResult<MovieDetail>> {
//        return flow {
//            emit(
//                safeApiCall {
//
//                    provideMovieService.getMovieDetail(authToken,id ,language)
//                }
//            )
//        }.flowOn(Dispatchers.IO)
//            .applyCommonSideEffects().catch {
//                emit(ResponseResult.Error(it.toString() + "Error occurred"))
//            }
//    }
}
