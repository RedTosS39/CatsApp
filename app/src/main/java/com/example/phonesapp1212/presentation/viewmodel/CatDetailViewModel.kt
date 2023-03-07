package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.repository.Repository
import kotlinx.coroutines.launch

class CatDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _mMutableLiveData = MutableLiveData<CatBreedDetails>()
    val liveData: LiveData<CatBreedDetails> = _mMutableLiveData

    fun getCatInfo(id: String) {
        viewModelScope.launch { _mMutableLiveData.postValue(repository.getBreed(id)) }
    }
}