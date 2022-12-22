package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _dataIsLoaded = MutableLiveData<Boolean>()
    val isDataLoaded: LiveData<Boolean> = _dataIsLoaded

    init {
        viewModelScope.launch {
            delay(3000)
            _dataIsLoaded.value = true
        }
    }
}