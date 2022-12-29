package com.example.phonesapp1212.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.repository.CatBreedRepository
import com.example.data.web.repository.CatBreedRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatDetailViewModel : ViewModel() {

    //insert di next
    private val catBreedRepository: CatBreedRepository = CatBreedRepositoryImpl()
    private val mMutableLiveData = MutableLiveData<CatBreedDetails>()
    val liveData: LiveData<CatBreedDetails> = mMutableLiveData

    fun getCatInfo(id: String) {
        viewModelScope.launch {
            val  response = catBreedRepository.getBreed(id)
            response.enqueue(object : Callback<CatBreedDetails> {
                override fun onResponse(call: Call<CatBreedDetails>, response: Response<CatBreedDetails>) {
                    Log.d("pokemon", "onResponse: ${call.request()}")
                    if (response.body() != null) {
                        Log.d("pokemon", "onResponse: ${response.body()}")

                        mMutableLiveData.postValue(response.body())
                        val breedId = response.body()!!.id
                    }
                }

                override fun onFailure(call: Call<CatBreedDetails>, t: Throwable) {

                    Log.d("pokemon", "onResponse: ${t.toString()}")
                }
            })
        }
    }
}