package com.example.phonesapp1212.di


import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.Repository
import com.example.data.web.repository.RepositoryImpl
import com.example.data.web.retrofit.CatApiServices
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RoomModule::class])
class AppModule {

    @Provides
    fun provideCatBreedRepository(
        catApiServices: CatApiServices,
        catDatabaseRepository: CatDatabaseRepository
    ): Repository {
        return RepositoryImpl(catApiServices, catDatabaseRepository)
    }
}