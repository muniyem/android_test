package com.example.androidtest.helpers

object NetworkHelper {
    const val MAX_RETRIES = 3L
    const val INITIAL_BACKOFF = 3000L
    fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)

}