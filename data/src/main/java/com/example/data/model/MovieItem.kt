package com.example.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(

    val bio: String,
    val createdby: String,
    val firstappearance: String,
    @SerializedName("url")
    val imageurl: String,
    @SerializedName("name")
    val name: String,
    val publisher: String,
    val realname: String,
    val team: String
)