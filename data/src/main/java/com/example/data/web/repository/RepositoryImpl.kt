package com.example.data.web.repository

import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.data.web.model.cats.BreedModel
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.retrofit.CatApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val catDatabaseRepository: CatDatabaseRepository
) : Repository {
    private val catApiServices: CatApiServices = CatApiServices.create()
    override suspend fun getBreed(id: String): CatBreedDetails {
        return withContext(Dispatchers.IO) { catApiServices.getCatByBreed(id) }
    }

    override suspend fun getCatList(): List<Breed> {

        //cast response to map
        return map(catApiServices.getCatsList())
    }

    private suspend fun map(list: List<Breed>): List<Breed> {

        return withContext(Dispatchers.IO) {

            list.map {
                if (catDatabaseRepository.findItemByTitle(it.name)) {
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

    override suspend fun getImageById(id: String): BreedModel {
        return withContext(Dispatchers.IO) { catApiServices.getImageById(breed_ids = id) }
    }
}