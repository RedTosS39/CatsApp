package com.example.phonesapp1212.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.cats.BreedModel
import com.example.data.model.cats.BreedModelItem
import com.example.data.model.cats.CatBreedDetails
import com.example.data.repository.CatBreedRepository
import com.example.data.repository.CatBreedRepositoryImpl
import com.example.data.repository.GetImageByIdRepository
import com.example.data.repository.GetImageByIdRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentCatViewModel : ViewModel() {

    //insert di next
    private val getImageByIdRepository: GetImageByIdRepository = GetImageByIdRepositoryImpl()
    private val mMutableLiveData = MutableLiveData<BreedModel>()
    val liveData: LiveData<BreedModel> = mMutableLiveData


    fun getCatInfo(breedId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val  response = getImageByIdRepository.getImageById(id = breedId)
            response.enqueue(object : Callback<BreedModel> {
                override fun onResponse(call: Call<BreedModel>, response: Response<BreedModel>) {
                    Log.d("pokemon", "CurrentCatViewModel URL: ${call.request()}")

                    if (response.body() != null) {
                        Log.d("pokemon", "getImageById response: ${response.body()}")

                        mMutableLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<BreedModel>, t: Throwable) {
                    Log.d("pokemon", "onResponse: ${t.toString()}")
                }
            })
        }
    }
}