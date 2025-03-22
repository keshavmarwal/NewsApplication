package com.example.newapplication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.Models.NewsModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel: ViewModel() {

    val responce =mutableStateOf<NewsModels?>(null)
 val repo =Repo()
    init{
        featchNews()
        Log.d("TAG", "MyViewModel: ${responce.value}")
    }
    fun featchNews(){
        viewModelScope.launch(Dispatchers.IO){
         val data = repo.newsProvider(
             country = "us",
             category = "business"

         )
            responce.value=data.body()
            Log.d("TAG", "featchNews: ${data.body()}")
        }
    }




}