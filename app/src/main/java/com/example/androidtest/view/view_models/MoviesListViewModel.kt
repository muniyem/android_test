package com.example.androidtest.view.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.androidtest.constants.Constants
import com.example.androidtest.paging_source.GetMoviesPagingSource
import com.example.androidtest.repo.MoviesRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MoviesRemoteRepository,
) : ViewModel() {


    private fun getMovies() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            GetMoviesPagingSource(repository,Constants.AUTH_TOKEN)
        }
    ).flow

    val listMovies = getMovies().cachedIn(viewModelScope)



}