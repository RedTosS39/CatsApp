package com.example.data.repository

import com.example.data.model.cats.BreedModel
import com.example.data.retrofit.CatApiServices
import retrofit2.Call

class GetImageByIdRepositoryImpl : GetImageByIdRepository {

    private val apiServices = CatApiServices.create()

    override suspend fun getImageById(id: String): Call<BreedModel> {
        return apiServices.getImageById(breed_ids = id)
    }
}