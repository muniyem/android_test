package com.example.androidtest.view.view_models
import androidx.lifecycle.MutableLiveData 
import androidx.lifecycle.ViewModel 
   
class SharedViewModel : ViewModel() { 
   
    val movieId = MutableLiveData<Int>()
   
    fun sendMovieId(id: Int) {
        movieId.value = id
    } 
}