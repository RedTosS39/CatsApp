package com.example.data.web.repository

import com.example.data.web.model.cats.Breed
import retrofit2.Call
import retrofit2.Response

interface CatListRepository {

     suspend fun getCatList() : Call<List<Breed>>
}