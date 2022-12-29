package com.example.data.web.model.cats

data class CatModelItem(
    val breeds: List<Any>,
    val favourite: Favourite,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)