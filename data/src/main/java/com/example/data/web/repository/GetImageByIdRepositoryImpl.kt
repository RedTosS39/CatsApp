package com.example.data.web.repository

import com.example.data.web.model.cats.BreedModel
import com.example.data.web.retrofit.CatApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

class GetImageByIdRepositoryImpl (val apiServices: CatApiServices) : GetImageByIdRepository {

    override suspend fun getImageById(id: String): BreedModel {
        return withContext(Dispatchers.IO) { apiServices.getImageById(breed_ids = id) }
    }
}