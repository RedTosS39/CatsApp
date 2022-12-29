package com.example.data.room.repository

import androidx.annotation.WorkerThread
import com.example.data.room.dao.CatDao
import com.example.data.room.model.CatEntity
import kotlinx.coroutines.flow.Flow

class CatDatabaseRepositoryImp(private val catDao: CatDao) : CatDatabaseRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(catEntity: CatEntity) {
        catDao.insert(catEntity)
    }

    override fun getAll(): Flow<List<CatEntity>> {
        return catDao.getAll()
    }

    override suspend fun deleteById(title: String) {
        catDao.deleteById(title)
    }
}