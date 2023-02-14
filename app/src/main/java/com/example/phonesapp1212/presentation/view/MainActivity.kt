package com.example.phonesapp1212.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ADD_TO_FAVORITE
import com.example.phonesapp1212.constants.Constants.DELETE_FROM_FAVORITE
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.constants.Constants.SHOW_SAVED
import com.example.phonesapp1212.presentation.adapters.CatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.*
import com.example.phonesapp1212.repository.IClickable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity(), IClickable {

    private val splashViewModel: SplashViewModel by viewModels()
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val testViewModel: TestViewModel by viewModels { TestViewModelFactory(database.catDao()) }
    private lateinit var catsAdapter: CatsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var fab: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        startSplash()

        testViewModel.getCatResponse()
        fab = findViewById(R.id.fab)
        progressBar = findViewById(R.id.progress_circular)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        catsAdapter = CatsAdapter(this@MainActivity)
        recyclerView.adapter = catsAdapter
        initCatListFromApi()

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                putExtra(SHOW_SAVED, "0")
            })
        }
    }

    private fun initCatListFromApi() {
        // mainViewModule.apply {
        testViewModel.catList.observe(this) {
            it?.let {
                Log.d("pokemon", "it: ${it.get(0).isFavorite} ")
                catsAdapter.submitList(it)

            }
            recyclerView.isVisible = true
            progressBar.isVisible
        }
    }

    //get key from clicked item in adapter
    override fun onClickListener(key: String, item: String) {
        //send item by key
        when (key) {
            //to details activity
            ID -> {
                startActivity(
                    Intent(this@MainActivity, CatDetailActivity::class.java).apply {
                        putExtra(key, item)
                    })
            }

            else -> {
                testViewModel.updateItem(key, item)
            }

        }
    }

    private fun startSplash() {
        //splash screen waiting for result
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    if (splashViewModel.isDataLoaded.value == true) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                    }
                    // The content is not ready; suspend.
                    return false
                }
            }
        )
    }
}