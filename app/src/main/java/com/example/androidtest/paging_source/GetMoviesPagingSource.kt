package com.example.androidtest.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidtest.models.MoviesItem
import com.example.androidtest.repo.MoviesRemoteRepository
import java.lang.Exception

class GetMoviesPagingSource(
    private val repository: MoviesRemoteRepository,
    private val authToken: String,

) : PagingSource<Int, MoviesItem>() {


    override fun getRefreshKey(state: PagingState<Int, MoviesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }



    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesItem> {
        return try {
            val position = params.key ?:1
            val response = repository.getMoviesList("$authToken","en-US",position)

            return LoadResult.Page(
                data = response?.body()?.results!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response?.body()?.total_pages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}