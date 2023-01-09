package com.example.phonesapp1212.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.dao.CatDao
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.room.repository.CatDatabaseRepositoryImp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RoomViewModel(private val catDatabaseRepository: CatDatabaseRepository) : ViewModel() {



    private val mGetAllCats: MutableLiveData<List<CatEntity>> = catDatabaseRepository.getAll().asLiveData() as MutableLiveData<List<CatEntity>>
    val getAllCats: LiveData<List<CatEntity>> = mGetAllCats

    fun insert(catEntity: CatEntity) {

        viewModelScope.launch {
            catDatabaseRepository.insert(catEntity)
        }
    }

    fun delete(title: String) {
        viewModelScope.launch {
            catDatabaseRepository.deleteById(title)
        }
    }

}