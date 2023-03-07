package com.example.data.web.repository

import com.example.data.web.model.cats.Breed
import com.example.data.web.model.cats.BreedModel
import com.example.data.web.model.cats.CatBreedDetails

interface Repository {

    suspend fun getBreed(id: String) : CatBreedDetails

    suspend fun getCatList() : List<Breed>

    suspend fun getImageById(id: String) : BreedModel
}