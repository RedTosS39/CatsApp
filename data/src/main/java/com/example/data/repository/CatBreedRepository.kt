package com.example.data.repository

import com.example.data.model.cats.Breed
import com.example.data.model.cats.CatBreedDetails
import retrofit2.Call

interface CatBreedRepository {

    suspend fun getBreed(id: String) : Call<CatBreedDetails>
}