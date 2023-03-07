package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.web.model.cats.BreedModel
import com.example.data.web.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentCatViewModel(val repository: Repository) : ViewModel() {

    //insert di next
//    private val repository: Repository = RepositoryImpl()
    private val mMutableLiveData = MutableLiveData<BreedModel>()
    val liveData: LiveData<BreedModel> = mMutableLiveData


    fun getCatInfo(breedId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            mMutableLiveData.postValue(repository.getImageById(breedId))

        }
    }
}