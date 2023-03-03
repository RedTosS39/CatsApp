package com.example.data.web.repository

import com.example.data.web.model.cats.Breed
import com.example.data.web.model.cats.CatBreedDetails
import retrofit2.Call

interface CatBreedRepository {

    suspend fun getBreed(id: String) : CatBreedDetails
}