package com.example.data.repository

import com.example.data.room.model.CatEntity
import com.example.data.web.model.cats.Breed
import com.example.data.web.repository.CatBreedRepository
import com.example.phonesapp1212.domain.model.Cat
import com.example.phonesapp1212.domain.repository.FromBreedToCatRepository

class FromBreedToCatRepositoryImpl(
    val catEntity: CatEntity?,
    val breed: Breed?,
    catBreedRepository: CatBreedRepository
) : FromBreedToCatRepository {

    override fun fromBreedToCat(): Cat {
        val cat = breed?.let {
            Cat(
                it.name,
                breed.description,
                breed.reference_image_id,
                breed.name == (catEntity?.title ?: false)
            )
        }
        return cat!!
    }
}