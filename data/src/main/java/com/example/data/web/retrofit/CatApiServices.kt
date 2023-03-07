package com.example.data.web.retrofit

import com.example.data.web.model.cats.Breed
import com.example.data.web.model.cats.BreedModel
import com.example.data.web.model.cats.CatBreedDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CatApiServices {

    //Get cat list
    @GET("breeds")
    suspend fun getCatsList() : List<Breed>

    //Get cat image by id
    @GET("images/{id}")
    suspend fun getCatByBreed(@Path("id") id: String) : CatBreedDetails

    //Get Array of images by breed id
    @GET("images/search")
    suspend fun getImageById(
        @Query("limit") limit: Int = 10,
        @Query("breed_ids") breed_ids: String,
        @Query("api_key") api_key: String = "REPLACE_ME") : BreedModel


//    companion object  {
//
//        private const val BASE_URL = "https://api.thecatapi.com/v1/"
//
//        fun create() : CatApiServices {
//
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(CatApiServices::class.java)
//
//        }
//    }
}