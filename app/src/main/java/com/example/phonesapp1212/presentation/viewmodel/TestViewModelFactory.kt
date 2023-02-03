package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.CatListRepository

class TestViewModelFactory(
    private val catDatabaseRepository: CatDatabaseRepository,
    private val catListRepository: CatListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(catDatabaseRepository, catListRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}