package com.example.data.model.cats

data class CatBreedDetails(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)