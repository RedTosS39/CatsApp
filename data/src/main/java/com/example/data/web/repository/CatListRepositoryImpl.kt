package com.example.data.web.repository

import android.util.Log
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.data.web.retrofit.CatApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatListRepositoryImpl(private val catDatabaseRepository: CatDatabaseRepository) : CatListRepository {
    private val catApiServices = CatApiServices.create()
    private val list = mutableListOf<Breed>()
    override suspend fun getCatList(): List<Breed> {
        val response = catApiServices.getCatsList()
        Log.d("pokemon", "getCatList: ${response[0].vcahospitals_url ?: "Nooooo"} ")
        return map(response)
    }

    private suspend fun map(list: List<Breed>) : List<Breed> {
        return withContext(Dispatchers.IO) {
            list.map {
                it.copy(isFavorite = catDatabaseRepository.findItemByTitle(it.name))
            }
        }
    }
}