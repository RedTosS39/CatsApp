package com.example.data.repository

import com.example.data.model.cats.BreedModel
import retrofit2.Call

interface GetImageByIdRepository {

    suspend fun getImageById(id: String) : Call<BreedModel>
}