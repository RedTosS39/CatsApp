package com.example.phonesapp1212.presentation.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
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

class TestViewModel(
    private val catListRepository: CatListRepository,
    private val catDatabaseRepository: CatDatabaseRepository,
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
            Log.d("pokemon", "VM getCatResponse: ${catListRepository.getCatList().size}")
            _catList.postValue(catListRepository.getCatList())
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