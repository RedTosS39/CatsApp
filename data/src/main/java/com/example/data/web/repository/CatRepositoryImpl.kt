package com.example.data.web.repository

import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.domain.model.Cat
import com.example.phonesapp1212.domain.repository.CatRepository
import java.util.jar.Attributes.Name

class CatRepositoryImpl(private val catDatabaseRepository: CatDatabaseRepository) {

     suspend fun getCatList(list: List<Breed>): List<Cat> {

             return list.map {
                 val isFavorite = catDatabaseRepository.findItemByTitle(it.name)
                 Cat(it.name, it.description, it.reference_image_id, isFavorite)
             }
    }
}