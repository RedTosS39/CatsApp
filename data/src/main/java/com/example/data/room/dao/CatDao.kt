package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.example.data.room.model.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Query("SELECT * FROM cat_table")
    fun getAll() : Flow<List<CatEntity>>

    @Insert(onConflict = IGNORE)
    suspend  fun insert(catEntity: CatEntity)

    @Query("DELETE FROM cat_table")
    suspend fun deleteById()

    @Query("DELETE FROM cat_table WHERE title = :title")
    suspend fun deleteItem(title: String)

    @Query("SELECT EXISTS (SELECT * FROM cat_table WHERE title = :title)")
    suspend fun findItemByTitle(title: String) : Boolean
}