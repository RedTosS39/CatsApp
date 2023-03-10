package com.example.phonesapp1212.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.data.web.repository.Repository
import com.example.phonesapp1212.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestViewModel @Inject constructor(
    val repository: Repository,
    val catDatabaseRepository: CatDatabaseRepository,
    application: Application
) : AndroidViewModel(application) {

//    private val catDatabaseRepository: CatDatabaseRepository = CatDatabaseRepositoryImp(catDao)
//    private val catListRepository: CatListRepository = CatListRepositoryImpl(catDao, catDatabaseRepository)

    private val _catList = MutableLiveData<List<Breed>?>()
    val catList: MutableLiveData<List<Breed>?> = _catList

    init {
        getCatResponse()
    }

    private fun getCatResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            _catList.postValue(repository.getCatList())
        }
    }

    /**
     * update list by chosen item in adapter.
     *
     * @param [key] const
     * @param [name] name of current position
     */
    fun updateItem(key: String, name: String) =
        viewModelScope.launch {
            when (key) {
                Constants.ADD_TO_FAVORITE -> {
                    val catEntity = CatEntity(null, name, "Desc", "Url")
                    Toast.makeText(getApplication(), "$name added to Database", Toast.LENGTH_SHORT)
                        .show()
                    catDatabaseRepository.insert(catEntity)
                    getCatResponse()
                }

                Constants.DELETE_FROM_FAVORITE -> {
                    catDatabaseRepository.deleteItem(name)
                    Toast.makeText(
                        getApplication(),
                        "$name removed from Database",
                        Toast.LENGTH_SHORT
                    ).show()
                    getCatResponse()
                }
            }
        }


}