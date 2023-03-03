package com.example.data.web.repository

import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import retrofit2.Call
import retrofit2.Response

interface CatListRepository {
     /**
      * Repository provides information taken from Cat API
      */

     suspend fun getCatList() : List<Breed>
}