package com.example.phonesapp1212.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.phonesapp1212.R
import com.example.phonesapp1212.presentation.adapters.FavoriteCatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FavoriteActivity : AppCompatActivity() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val repository by lazy { CatDatabaseRepositoryImp(database.catDao()) }
    private val recyclerView = findViewById<RecyclerView>(R.id.favorite_recycler)
    private val adapter = FavoriteCatsAdapter()
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()

        roomViewModel.getAllCats.observe(this) { catEntity ->
            catEntity?.let {
                adapter.submitList(it)
            }
        }
    }

    private fun init() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}