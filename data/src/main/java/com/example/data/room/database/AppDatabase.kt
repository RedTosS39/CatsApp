package com.example.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.dao.CatDao
import com.example.data.room.model.CatEntity

@Database(entities = [CatEntity::class], version = 2)

abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

//    companion object {
//        private const val DATABASE_NAME = "Cats_database"
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    DATABASE_NAME
//                )
//                    .fallbackToDestructiveMigration()
//                    //.addCallback(CatDatabaseCallback(applicationScope))
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
}