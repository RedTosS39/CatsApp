package com.example.phonesapp1212.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.web.model.cats.Breed
import com.example.data.web.repository.CatListRepository
import com.example.data.web.repository.CatListRepositoryImpl
import com.example.data.web.repository.CatRepositoryImpl
import com.example.phonesapp1212.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    //insert di next
    private val catListRepository : CatListRepository = CatListRepositoryImpl()

    private val _catList = MutableLiveData<List<Breed>>()
    val catList: LiveData<List<Breed>> = _catList

    //get list of cats
     fun getCatsResponse()  {
         viewModelScope.launch(Dispatchers.IO) {
             val response = catListRepository.getCatList()
             response.enqueue(object : Callback<List<Breed>> {
                 override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                     if (response.body() != null) {
//                         Log.d("pokemon", "onResponse: ${response.body()!![0].reference_image_id}")

                         _catList.postValue(response.body())
                     }
                 }

                 override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                     Log.d("pokemon", "onFailure $t")
                 }
             })
         }
    }
}