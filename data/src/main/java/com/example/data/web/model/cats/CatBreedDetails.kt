package com.example.data.web.model.cats

import com.google.gson.annotations.Expose

data class CatBreedDetails(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
//    @Expose(serialize = false, deserialize = false)
//    var isSavedLocal: Boolean = false
)