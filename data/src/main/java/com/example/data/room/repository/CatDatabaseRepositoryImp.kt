package com.example.data.room.repository

import androidx.annotation.WorkerThread
import com.example.data.room.dao.CatDao
import com.example.data.room.model.CatEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatDatabaseRepositoryImp @Inject constructor(private val catDao: CatDao) : CatDatabaseRepository {

    //it will notify the observer when the data has changed
    private val allCats: Flow<List<CatEntity>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(catEntity: CatEntity) {
        catDao.insert(catEntity)
    }

    override fun getAll(): Flow<List<CatEntity>> {
        return allCats
    }

    override suspend fun deleteById() {
        catDao.deleteById()
    }

    override suspend fun deleteItem(title: String) {
        catDao.deleteItem(title)
    }

    override suspend fun findItemByTitle(title: String) : Boolean {
        return catDao.findItemByTitle(title)

    }
}