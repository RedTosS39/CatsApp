
package com.example.phonesapp1212.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.CatListRepository

class TestViewModelFactory(
    private val catListRepository: CatListRepository,
    private val catDatabaseRepository: CatDatabaseRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(catListRepository, catDatabaseRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}