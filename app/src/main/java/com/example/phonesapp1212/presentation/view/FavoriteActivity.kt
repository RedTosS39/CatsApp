package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ADD_TO_FAVORITE
import com.example.phonesapp1212.constants.Constants.DELETE_FROM_FAVORITE
import com.example.phonesapp1212.constants.Constants.SHOW_SAVED
import com.example.phonesapp1212.presentation.adapters.FavoriteCatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FavoriteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteCatsAdapter
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val repository by lazy { CatDatabaseRepositoryImp(database.catDao()) }
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()

        //roomViewModel.delete()
        val showSaved = intent.getStringExtra(SHOW_SAVED)
        if(showSaved != null) {
            roomViewModel.getAllCats
        }

        roomViewModel.getAllCats.observe(this) {
            it.let {
                adapter.submitList(it)
            }
        }
    }

    private fun init() {
        adapter = FavoriteCatsAdapter()
        recyclerView = findViewById(R.id.favorite_recycler)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}