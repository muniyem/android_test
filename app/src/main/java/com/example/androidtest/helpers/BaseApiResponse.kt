package com.example.androidtest.helpers

import com.example.androidtest.models.Movies
import retrofit2.Response

/**
 * abstract class with method for calling the API and return the response wrapped in sealed class of
 * ResponseResult
 */
abstract class BaseApiResponse {

    suspend fun <T : Any> safeApiCall(apiCall: () -> Response<T>?): ResponseResult<T> {

            val response = apiCall()

            if (response?.isSuccessful == true) {
                val body = response.body()
                body?.let {
                    return ResponseResult.Success(body)
                }
            }

//            if (response.raw().networkResponse?.request?.url.toString()
//                    .contains("api-dev.digilifeinc.com")
//            ) {
//                val errorResponse =
//                    Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)
//                return ResponseResult.Error("${errorResponse.code.toString()} ${errorResponse.message.toString()}")
//            }


            return ResponseResult.Error("${response?.code()} ${response?.message()}")
    }


}