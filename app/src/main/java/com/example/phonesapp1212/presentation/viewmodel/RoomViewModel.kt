package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepository
import kotlinx.coroutines.launch

class RoomViewModel(catDatabaseRepository: CatDatabaseRepository) : ViewModel() {

    val getAllCats: LiveData<List<CatEntity>> = catDatabaseRepository.getAll().asLiveData()
}