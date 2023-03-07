package com.example.phonesapp1212.di

import android.app.Application
import android.content.Context
import com.example.phonesapp1212.presentation.view.CatDetailActivity
import com.example.phonesapp1212.presentation.view.FavoriteActivity
import com.example.phonesapp1212.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent.Factory

@Component (modules = [AppModule::class])

interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(favoriteActivity: FavoriteActivity)
    fun inject(catDetailActivity: CatDetailActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}