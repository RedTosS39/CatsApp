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

class CatDetailViewModel : ViewModel() {

    //insert di next
    private val catBreedRepository: CatBreedRepository = CatBreedRepositoryImpl()

    private val _mMutableLiveData = MutableLiveData<CatBreedDetails>()
    val liveData: LiveData<CatBreedDetails> = _mMutableLiveData

    fun getCatInfo(id: String) {
        viewModelScope.launch { _mMutableLiveData.postValue(catBreedRepository.getBreed(id)) }
    }
}