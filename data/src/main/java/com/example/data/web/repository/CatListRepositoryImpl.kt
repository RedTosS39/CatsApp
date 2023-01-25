package com.example.data.web.repository

import com.example.data.web.model.cats.Breed
import com.example.data.web.retrofit.CatApiServices
import retrofit2.Call


class CatListRepositoryImpl : CatListRepository {

    private val catApiServices = CatApiServices.create()

    override suspend fun getCatList(): Call<List<Breed>> {
        return catApiServices.getCatsList()
    }

}