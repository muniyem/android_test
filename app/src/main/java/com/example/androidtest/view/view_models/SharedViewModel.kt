package com.example.androidtest.view.view_models
import androidx.lifecycle.MutableLiveData 
import androidx.lifecycle.ViewModel 
   
class SharedViewModel : ViewModel() { 
   
    // variable to contain message whenever 
    // it gets changed/modified(mutable) 
    val MovieId = MutableLiveData<Int>()
   
    // function to send message 
    fun sendMovieId(id: Int) {
        MovieId.value = id
    } 
}