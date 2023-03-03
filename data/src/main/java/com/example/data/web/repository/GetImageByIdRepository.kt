package com.example.data.web.repository

import com.example.data.web.model.cats.BreedModel
import retrofit2.Call

interface GetImageByIdRepository {

    suspend fun getImageById(id: String) : BreedModel
}