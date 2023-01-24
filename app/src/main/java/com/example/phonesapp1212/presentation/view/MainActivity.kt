package com.example.phonesapp1212.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.database.AppDatabase
import com.example.data.room.repository.CatDatabaseRepositoryImp
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ADD_TO_FAVORITE
import com.example.phonesapp1212.constants.Constants.DELETE_FROM_FAVORITE
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.constants.Constants.SHOW_SAVED
import com.example.phonesapp1212.presentation.adapters.CatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.MainViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModel
import com.example.phonesapp1212.presentation.viewmodel.RoomViewModelFactory
import com.example.phonesapp1212.presentation.viewmodel.SplashViewModel
import com.example.phonesapp1212.repository.IClickable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MainActivity : AppCompatActivity(), IClickable {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    private val repository by lazy { CatDatabaseRepositoryImp(database.catDao()) }
    private val roomViewModel: RoomViewModel by viewModels {
        RoomViewModelFactory(repository)
    }

    private val splashViewModel: SplashViewModel by viewModels()
    private val mainViewModule: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private lateinit var catsAdapter: CatsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        startSplash()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        initCatListFromApi()
        mainViewModule.getCatsResponse()

    }

//    private fun getDatabaseStatus() : Map<String, Boolean> {
//
//        val title = mutableMapOf<String, Boolean>()
//        roomViewModel.getAllCats.observe(this) {
//            for(i in it) {
//                title[i.title] = true
//                Log.d("pokemon", "getDatabaseStatus: ${i.title} ")
//            }
//        }
//        return title
//    }

    private fun initCatListFromApi() {
        mainViewModule.apply {
            catList.observe(this@MainActivity) {
                it?.let {
                    catsAdapter = CatsAdapter(this@MainActivity)
                    catsAdapter.submitList(it)
                    recyclerView.adapter = catsAdapter
                }
            }
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

            //to database
            ADD_TO_FAVORITE -> {
                startActivity(
                    Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                    putExtra(key, item)
                })
            }

            SHOW_SAVED -> {
                startActivity(
                    Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                        putExtra(key, "0")
                    })
            }

            DELETE_FROM_FAVORITE -> {
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                    putExtra(key, "delete")
                })
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