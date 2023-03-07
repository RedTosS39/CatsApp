package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.web.repository.Repository
import javax.inject.Inject

class CurrentCatViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentCatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrentCatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}