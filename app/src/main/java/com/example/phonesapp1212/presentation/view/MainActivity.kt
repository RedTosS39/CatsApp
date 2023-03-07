package com.example.phonesapp1212.presentation.view

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
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.data.web.repository.RepositoryImpl
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.di.App
import com.example.phonesapp1212.di.AppComponent
import com.example.phonesapp1212.presentation.adapters.CatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.SplashViewModel
import com.example.phonesapp1212.presentation.viewmodel.TestViewModel
import com.example.phonesapp1212.presentation.viewmodel.TestViewModelFactory
import com.example.phonesapp1212.repository.IClickable

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

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val catDatabaseRepository by lazy { CatDatabaseRepositoryImp(database.catDao()) }
    private val repository by lazy { RepositoryImpl() }

    private val testViewModel: TestViewModel by viewModels {
        TestViewModelFactory(
            repository,
            catDatabaseRepository,
            application
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

//        (applicationContext as ApplicationComponent).inject(this)
        startSplash()

        progressBar = findViewById(R.id.progress_circular)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        catsAdapter = CatsAdapter(this@MainActivity)
        recyclerView.adapter = catsAdapter
        initCatListFromApi()
    }

    private fun initCatListFromApi() {

        testViewModel.catList.observe(this) { innerList ->
            innerList?.let {
                Log.d("pokemon", "initCatListFromApi: ${it.size}")
                catsAdapter.submitList(it)
            }
            recyclerView.isVisible = true
            progressBar.isVisible
            supportActionBar?.show()
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
