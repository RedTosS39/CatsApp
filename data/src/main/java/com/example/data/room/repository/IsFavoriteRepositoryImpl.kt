package com.example.data.room.repository

import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import com.example.data.web.model.cats.Breed
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.retrofit.CatApiServices
import com.example.data.repository.FromBreedToCatRepositoryImpl
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class IsFavoriteRepositoryImpl(private val database: AppDatabase, val list: List<CatBreedDetails>)  {

    //create call
     suspend fun getBreed(id: String): Call<CatBreedDetails> {

        val catApiServices = CatApiServices.create()

        return catApiServices.getCatByBreed(id)
     }

    suspend fun markAsFavorite(call: Call<CatBreedDetails>) : List<CatBreedDetails> {
        val allCats: Flow<List<CatEntity>> = database.catDao().getAll()

        return list
    }
}