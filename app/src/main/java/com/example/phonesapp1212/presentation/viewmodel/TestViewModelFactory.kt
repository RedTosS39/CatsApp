
package com.example.phonesapp1212.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.CatListRepository
import com.example.data.web.repository.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class TestViewModelFactory @AssistedInject constructor(
     val repository: Repository,
     val catDatabaseRepository: CatDatabaseRepository,
    @Assisted("application") val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TestViewModel(repository, catDatabaseRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    @AssistedFactory
    interface Factory {
        fun create( @Assisted("application") application: Application) : TestViewModelFactory
    }
}