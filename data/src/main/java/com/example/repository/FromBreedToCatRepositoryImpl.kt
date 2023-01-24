package com.example.repository

import com.example.data.room.model.CatEntity
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.domain.model.Cat
import com.example.phonesapp1212.domain.repository.FromBreedToCatRepository

class FromBreedToCatRepositoryImpl(val catEntity: CatEntity?, val breed: Breed?) :
    FromBreedToCatRepository {

    fun mapToCat() {
        val cat = breed?.let {
            Cat(
                it.name,
                breed.description,
                breed.reference_image_id,
                breed.name == (catEntity?.title ?: false)
            )
        }
        if (cat != null) {
            fromBreedToCat(cat)
        }
    }

    override fun fromBreedToCat(cat: Cat?): Cat {
        return cat as Cat
    }
}