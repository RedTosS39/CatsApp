package com.example.data.retrofit

import com.example.data.model.cats.Breed
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CatApiServices {

    @GET("breeds")
    fun getCatByBreed() : Call<List<Breed>>

    companion object  {

        private const val BASE_URL = "https://api.thecatapi.com/v1/"

        fun create() : CatApiServices {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CatApiServices::class.java)

        }
    }
}