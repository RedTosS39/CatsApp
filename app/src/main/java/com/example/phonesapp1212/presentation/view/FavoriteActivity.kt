package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.SHOW_SAVED
import com.example.phonesapp1212.presentation.adapters.FavoriteCatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModelFactory
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: RoomViewModelFactory

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteCatsAdapter
    private val roomViewModel: RoomViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()
        appComponent.inject(this)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_all_cats -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}