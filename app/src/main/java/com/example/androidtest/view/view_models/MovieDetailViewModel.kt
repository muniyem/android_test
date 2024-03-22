package com.example.androidtest.view.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtest.constants.Constants
import com.example.androidtest.models.MovieDetail
import com.example.androidtest.repo.MoviesRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MoviesRemoteRepository
) : ViewModel() {

    private var movieDetailJob: Job? = null
    var movieDetail: MutableLiveData<MovieDetail?> = MutableLiveData()
    var loadingStatus = MutableLiveData<Boolean>(false)


    fun movieDetailApiCall(id: Int) {
        if (movieDetailJob?.isActive == true) movieDetailJob?.cancel() else movieDetailJob

        movieDetailJob = viewModelScope.launch {


            val response = repository.movieDetail(Constants.AUTH_TOKEN, id,Constants.LANGUAGE)

            movieDetail.value = response?.body()




        }
    }



}