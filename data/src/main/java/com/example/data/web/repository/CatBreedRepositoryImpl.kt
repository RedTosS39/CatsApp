package com.example.data.web.repository

import android.util.Log
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.retrofit.CatApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CatBreedRepositoryImpl(private val catApiServices: CatApiServices) :
    CatBreedRepository {

    override suspend fun getBreed(id: String): CatBreedDetails {

        return withContext(Dispatchers.IO) { catApiServices.getCatByBreed(id) }
    }
}