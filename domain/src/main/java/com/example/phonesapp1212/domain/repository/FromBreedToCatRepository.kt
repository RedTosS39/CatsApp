package com.example.phonesapp1212.domain.repository

import com.example.phonesapp1212.domain.model.Cat

interface FromBreedToCatRepository {
    fun fromBreedToCat() : Cat
}