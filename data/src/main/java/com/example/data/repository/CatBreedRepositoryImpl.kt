package com.example.data.repository

import com.example.data.model.cats.Breed
import com.example.data.model.cats.CatBreedDetails
import com.example.data.retrofit.CatApiServices
import retrofit2.Call

class CatBreedRepositoryImpl : CatBreedRepository {

    override suspend fun getBreed(id: String): Call<CatBreedDetails> {

        val catApiServices = CatApiServices.create()

        return catApiServices.getCatByBreed(id)
    }
}