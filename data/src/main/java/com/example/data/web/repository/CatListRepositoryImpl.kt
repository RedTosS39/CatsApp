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
import kotlin.math.log


class CatListRepositoryImpl(private val catDatabaseRepository: CatDatabaseRepository) : CatListRepository {

    private val catApiServices = CatApiServices.create()

    override suspend fun getCatList(): List<Breed> {
        val response = catApiServices.getCatsList()
        Log.d("pokemon", "getCatList: ${response[0] ?: "Nooooo"} ")

           return map(response)
    }

    private suspend fun map(list: List<Breed>) : List<Breed> {

        Log.d("pokemon", "getCatList in map: ${list.size} ")
        return withContext(Dispatchers.IO) {
            Log.d("pokemon", "map: ${list.size}")
            for (i in list) {
                Log.d("pokemon", "map: $i")
            }
            list.map {
                if(catDatabaseRepository.findItemByTitle(it.name)) {
                    try {
                        it.copy(isFavorite = catDatabaseRepository.findItemByTitle(it.name))
                    } catch (e: Exception) {
                        it
                    }
                } else {
                    it
                }
            }
        }
    }
}