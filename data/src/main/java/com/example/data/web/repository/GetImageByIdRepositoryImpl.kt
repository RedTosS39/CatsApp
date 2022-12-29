package com.example.data.web.repository

import com.example.data.web.model.cats.BreedModel
import com.example.data.web.retrofit.CatApiServices
import retrofit2.Call

class GetImageByIdRepositoryImpl : GetImageByIdRepository {

    private val apiServices = CatApiServices.create()

    override suspend fun getImageById(id: String): Call<BreedModel> {
        return apiServices.getImageById(breed_ids = id)
    }
}