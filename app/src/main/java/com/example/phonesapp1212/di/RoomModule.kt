package com.example.phonesapp1212.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.dao.CatDao
import com.example.data.room.database.AppDatabase
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.room.repository.CatDatabaseRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDao(context: Application) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Cats_database").build()
    }

    @Provides
    fun provideCatDatabaseRepository(dao: CatDao) : CatDatabaseRepository {
        return CatDatabaseRepositoryImp(dao)
    }
}