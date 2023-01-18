package com.example.phonesapp1212.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.data.room.model.CatEntity
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.breed
import com.example.phonesapp1212.constants.Constants.deleteBreed
import com.example.phonesapp1212.constants.Constants.newFavoriteActivityRequestCode
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

        val item = intent.getStringExtra(breed)
        val deleteItem = intent.getStringExtra(deleteBreed)

        if(item != null) {
            val catEntity = CatEntity(null, item, "Desc", "Url")
            roomViewModel.insert(catEntity)
        } else if(deleteItem != null) {
            roomViewModel.deleteItem(deleteItem)
        } else {
            roomViewModel.getAllCats
        }



        roomViewModel.getAllCats.observe(this) {
            it.let {
                adapter.submitList(it)
            }
        }
    }

    private fun getKey(key: String) {
        when(key) {
            breed -> {

            }
            deleteBreed -> {

            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == newFavoriteActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            data?.getStringExtra(breed)?.let {
//                val catEntity = CatEntity(0, it, "description", "link")
//                roomViewModel.insert(catEntity)
//                Toast.makeText(applicationContext, "item has been added", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(applicationContext, "item hasn't been added", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun init() {
        adapter = FavoriteCatsAdapter()
        recyclerView = findViewById(R.id.favorite_recycler)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}