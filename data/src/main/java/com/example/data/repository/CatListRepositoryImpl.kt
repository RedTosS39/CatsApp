package com.example.data.repository

import com.example.data.model.cats.Breed
import com.example.data.retrofit.CatApiServices
import retrofit2.Call


class CatListRepositoryImpl : CatListRepository {

    private val catApiServices = CatApiServices.create()

    override suspend fun getCatList(): Call<List<Breed>> {
        return catApiServices.getCatsList()
    }
}