package com.example.phonesapp1212.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.data.web.repository.CatListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewModel(
    private val catDatabaseRepository: CatDatabaseRepository,
    private val catListRepository: CatListRepository
) : ViewModel() {

    private val _catList = MutableLiveData<List<Breed>>()
    val catList: LiveData<List<Breed>> = _catList

    private fun getCatResponse()  {

        viewModelScope.launch(Dispatchers.IO) {
            val response = catListRepository.getCatList()
            response.enqueue(object : Callback<List<Breed>> {
                override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {

                    response.body()?.apply {
                        _catList.postValue(this)
                    }
                }

                override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                    Log.d("pokemon", "onFailure $t")
                }
            })
        }
    }

    fun getFinalList() {
        viewModelScope.launch {
            getCatResponse()
            _catList.value?.filter {
                it.isFavorite
            }?.forEach { it.isFavorite = catDatabaseRepository.findItemByTitle(it.name) }
        }

        Log.d("pokemon", "done: ")
    }
}