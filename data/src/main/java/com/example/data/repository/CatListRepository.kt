package com.example.data.repository

import com.example.data.model.cats.Breed
import retrofit2.Call
import retrofit2.Response

interface CatListRepository {

     suspend fun getCatList() : Call<List<Breed>>
}