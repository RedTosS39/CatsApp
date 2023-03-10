package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.web.repository.Repository
import javax.inject.Inject

class CatDetailViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(CatDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatDetailViewModel(repository) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}