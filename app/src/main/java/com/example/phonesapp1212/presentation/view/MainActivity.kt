package com.example.phonesapp1212.presentation.view

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.data.room.repository.CatDatabaseRepository
import com.example.data.web.repository.Repository
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.di.App
import com.example.phonesapp1212.di.AppComponent
import com.example.phonesapp1212.presentation.adapters.CatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.SplashViewModel
import com.example.phonesapp1212.presentation.viewmodel.TestViewModel
import com.example.phonesapp1212.presentation.viewmodel.TestViewModelFactory
import com.example.phonesapp1212.repository.IClickable
import javax.inject.Inject

val Context.appComponent: AppComponent
get() = when(this) {
    is App -> appComponent
    else -> this.applicationContext.appComponent
}
class MainActivity : AppCompatActivity(), IClickable {
    private lateinit var catsAdapter: CatsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val splashViewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var factory: TestViewModelFactory.Factory

    private val testViewModel: TestViewModel by viewModels {
        factory.create(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        startSplash()
        init()
    }

    private fun init() {
        progressBar = findViewById(R.id.progress_circular)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        catsAdapter = CatsAdapter(this@MainActivity)
        recyclerView.adapter = catsAdapter

        testViewModel.catList.observe(this) { innerList ->
            innerList?.let {
                catsAdapter.submitList(it)
            }
            recyclerView.isVisible = true
            progressBar.isVisible
            supportActionBar?.show()
        }
    }

    //get key from clicked item in adapter
    override fun onClickListener(key: String, item: String) {
        when (key) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.show_all_favorites -> {
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
