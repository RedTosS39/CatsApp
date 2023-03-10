package com.example.phonesapp1212.di


import android.app.Application
import android.content.Context
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.Repository
import com.example.data.web.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [NetworkModule::class, RoomModule::class])
class AppModule {

    @Suppress("FunctionName")
    @Provides
    fun provideRepository(catDatabaseRepository: CatDatabaseRepository): Repository {
        return RepositoryImpl(catDatabaseRepository)
    }
}
