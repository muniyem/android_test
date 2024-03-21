package com.example.androidtest.helpers
/**
 * ResponseResult sealed class with three variants of Success , Error and Progress(Loading)
 */
sealed class ResponseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : ResponseResult<T>()

    class Error(val errMessage: String) : ResponseResult<Nothing>()

    class Progress(val isLoading: Boolean) : ResponseResult<Nothing>()
}