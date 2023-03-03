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

class CatBreedRepositoryImpl : CatBreedRepository {

    private val catApiServices = CatApiServices.create()

    override suspend fun getBreed(id: String): CatBreedDetails {

        return withContext(Dispatchers.IO) { catApiServices.getCatByBreed(id) }

//        var list = mutableListOf<CatBreedDetails>()
//        response.enqueue(object : Callback<CatBreedDetails> {
//            override fun onResponse(call: Call<CatBreedDetails>, response: Response<CatBreedDetails>) {
//                Log.d("pokemon", "onResponse: ${call.request()}")
//                if (response.body() != null) {
//                    Log.d("pokemon", "onResponse: ${response.body()}")
//                    list = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<CatBreedDetails>, t: Throwable) {
//
//                Log.d("pokemon", "onResponse: ${t.toString()}")
//            }
//        })
    }
}