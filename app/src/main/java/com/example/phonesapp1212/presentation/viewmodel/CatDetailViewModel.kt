package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.web.model.cats.BreedModel
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatDetailViewModel (val repository: Repository) : ViewModel() {

    private val _mMutableLiveData = MutableLiveData<CatBreedDetails>()
    val getCatInfoLiveData: LiveData<CatBreedDetails> = _mMutableLiveData


    private val mMutableLiveData = MutableLiveData<BreedModel>()
    val liveData: LiveData<BreedModel> =  mMutableLiveData

    fun getCatInfo(id: String) {
        viewModelScope.launch { _mMutableLiveData.postValue(repository.getBreed(id)) }
    }

    fun getCatIdInfo(breedId: String) {
        viewModelScope.launch {   mMutableLiveData.postValue(repository.getImageById(breedId)) }
    }
}