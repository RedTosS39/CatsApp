package com.example.data.web.repository

import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.retrofit.CatApiServices
import retrofit2.Call

class CatBreedRepositoryImpl : CatBreedRepository {

    override suspend fun getBreed(id: String): Call<CatBreedDetails> {

        val catApiServices = CatApiServices.create()

        return catApiServices.getCatByBreed(id)
    }
}