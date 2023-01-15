package com.example.data.room.repository

import com.example.data.room.model.CatEntity
import kotlinx.coroutines.flow.Flow

interface CatDatabaseRepository {

    suspend fun insert(catEntity: CatEntity)

    fun getAll() : Flow<List<CatEntity>>

    suspend fun deleteById()
}