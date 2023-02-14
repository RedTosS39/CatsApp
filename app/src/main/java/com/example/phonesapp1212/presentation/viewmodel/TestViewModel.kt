package com.example.phonesapp1212.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.data.room.dao.CatDao
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.data.web.model.cats.Breed
import com.example.data.web.repository.CatListRepository
import com.example.data.web.repository.CatListRepositoryImpl
import com.example.phonesapp1212.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewModel(catDao: CatDao) : ViewModel() {

    private val catDatabaseRepository: CatDatabaseRepository = CatDatabaseRepositoryImp(catDao)
    private val catListRepository: CatListRepository = CatListRepositoryImpl()
    private var _catList = MutableLiveData<List<Breed>>()
    val catList: LiveData<List<Breed>> = _catList

    fun getCatResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = catListRepository.getCatList()
            response.enqueue(object : Callback<List<Breed>> {
                override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                    viewModelScope.launch {
                        response.body()?.let { it ->
                            it.filter {
                                catDatabaseRepository.findItemByTitle(it.name)
                            }.forEach{
                                it.isFavorite = catDatabaseRepository.findItemByTitle(it.name)
                            }
                            _catList.postValue(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                    Log.d("pokemon", "onFailure $t")
                }
            })
        }
    }

    fun updateItem(key: String, name: String) {
        viewModelScope.launch {
            when (key) {
                Constants.ADD_TO_FAVORITE -> {
                    val catEntity = CatEntity(null, name, "Desc", "Url")
                    catDatabaseRepository.insert(catEntity)

                    val newCatList: MutableList<Breed>? = catList.value as MutableList<Breed>?
                    newCatList.let { list ->
                        list?.filter {
                            catDatabaseRepository.findItemByTitle(it.name)
                        }?.forEach{
                            it.isFavorite = catDatabaseRepository.findItemByTitle(it.name)
                        }

                        _catList.postValue(list)
                    }

                    
                }

                Constants.DELETE_FROM_FAVORITE -> {
                    catDatabaseRepository.deleteItem(name)
                    getCatResponse()

//                    _catList.value.let { breedList ->
//
//                        breedList?.filter {
//                            catDatabaseRepository.findItemByTitle(name)
//                        }?.forEach{
//                            it.isFavorite = catDatabaseRepository.findItemByTitle(name)
//                        }
//                        _catList.postValue(breedList)
//                    }
                }
            }
        }
    }

    fun addToFavorite(item: String) {
        val catEntity = CatEntity(null, item, "Desc", "Url")
        viewModelScope.launch {
            catDatabaseRepository.insert(catEntity)
        }
    }

    fun deleteFromFavorite(item: String) {
        viewModelScope.launch {
            catDatabaseRepository.deleteItem(item)
        }
    }
}