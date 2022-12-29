package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.room.repository.CatDatabaseRepository


class RoomViewModelFactory(private val catDatabaseRepository: CatDatabaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomViewModel(catDatabaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}