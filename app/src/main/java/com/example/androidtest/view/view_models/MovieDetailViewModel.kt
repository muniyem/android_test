package com.example.androidtest.view.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtest.constants.Constants
import com.example.androidtest.helpers.ResponseResult
import com.example.androidtest.helpers.cancelIfActive
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


    fun MovieDetailApiCall(id: Int) {

        movieDetailJob.cancelIfActive()

        movieDetailJob = viewModelScope.launch {


            val response = repository.movieDetail(Constants.AUTH_TOKEN, id,Constants.LANGUAGE)

            movieDetail.value = response?.body()

            Log.d("CHECKING",movieDetail.value?.genresListToString.toString())
            Log.d("CHECKING",movieDetail.value?.poster_path.toString())
            Log.d("CHECKING",movieDetail.value?.getImageURL.toString())


        /*.collect {

                when (it) {
                    is ResponseResult.Success -> {
                        movieDetail.value = it.data

                        Log.d("DETAIL",it.data.toString())


                    }
                    is ResponseResult.Progress -> {

                        loadingStatus.postValue(it.isLoading)

                    }
                    is ResponseResult.Error -> {
                        Log.d("Error", it.errMessage)
                    }

                }

            }*/


        }
    }



}