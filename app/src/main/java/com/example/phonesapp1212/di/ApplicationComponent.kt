package com.example.phonesapp1212.di

import com.example.phonesapp1212.presentation.view.MainActivity
import dagger.Component
import dagger.Subcomponent

@Component
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}