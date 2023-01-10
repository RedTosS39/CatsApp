package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.data.room.model.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Query("SELECT * FROM cat_table")
    fun getAll() : Flow<List<CatEntity>>

    @Insert(onConflict = REPLACE)
    suspend  fun insert(catEntity: CatEntity)

    @Query("DELETE FROM cat_table WHERE title=:title")
    suspend fun deleteById(title: String)
}