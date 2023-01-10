package com.example.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.room.dao.CatDao
import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [CatEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {


    abstract fun catDao(): CatDao

    private class CatDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {

                    val catDao = database.catDao()
                    catDao.deleteById("title")
                    val cat = CatEntity(0, "Cat", "Description", "image 1")
                    catDao.insert(cat)

                }
            }
        }
    }

    companion object {
        private const val DATABASE_NAME = "Cats_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}