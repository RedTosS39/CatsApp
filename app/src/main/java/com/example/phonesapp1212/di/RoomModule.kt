package com.example.phonesapp1212.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.room.dao.CatDao
import com.example.data.room.database.AppDatabase
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.room.repository.CatDatabaseRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    fun provideDatabase(context: Context) : AppDatabase {
        return AppDatabase.getDatabase(context = context)
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase) : CatDao {
        return appDatabase.catDao()
    }

    @Provides
    fun provideCatDatabaseRepository(dao: CatDao) : CatDatabaseRepository {
        return CatDatabaseRepositoryImp(dao)
    }
}