package com.example.data.room.repository

interface IsFavoriteRepository {

    suspend fun collect(id: String) : Boolean
}