package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.breed
import com.example.phonesapp1212.presentation.adapters.FavoriteCatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FavoriteActivity : AppCompatActivity() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val repository by lazy { CatDatabaseRepositoryImp(database.catDao()) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:FavoriteCatsAdapter
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()

        val item = intent.getStringExtra(breed)
        roomViewModel.getAllCats.observe(this) { list ->
            list?.forEach {

                val catEntity = CatEntity(it.id, item.toString(),it.description, it.image)
                Log.d("pokemon", "favorite catEntity:  $catEntity")
                roomViewModel.insert(catEntity)
                adapter.submitList(listOf(catEntity))
            }
        }

        roomViewModel.getAllCats
    }

    private fun init() {
        adapter = FavoriteCatsAdapter()
        recyclerView = findViewById(R.id.favorite_recycler)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}