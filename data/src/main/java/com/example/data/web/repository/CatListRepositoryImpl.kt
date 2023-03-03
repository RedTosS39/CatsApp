package com.example.data.web.repository

import android.util.Log
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.data.web.retrofit.CatApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CatListRepositoryImpl (private val catDatabaseRepository: CatDatabaseRepository) : CatListRepository {

    private val catApiServices = CatApiServices.create()

    override suspend fun getCatList(): List<Breed> {

        //cast response to map
           return map(catApiServices.getCatsList())
    }

    private suspend fun map(list: List<Breed>) : List<Breed> {

        return withContext(Dispatchers.IO) {

            list.map {
                if(catDatabaseRepository.findItemByTitle(it.name)) {
                    try {
                        it.copy(isFavorite = catDatabaseRepository.findItemByTitle(it.name))
                    } catch (e: Exception) {
                        it
                    }
                } else {
                    it
                }
            }
        }
    }
}