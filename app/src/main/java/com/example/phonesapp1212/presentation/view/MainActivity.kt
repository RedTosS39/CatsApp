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
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.breed
import com.example.phonesapp1212.constants.Constants.id
import com.example.phonesapp1212.presentation.adapters.CatsAdapter
import com.example.phonesapp1212.presentation.viewmodel.MainViewModel
import com.example.phonesapp1212.presentation.viewmodel.SplashViewModel
import com.example.phonesapp1212.repository.IClickable


class MainActivity : AppCompatActivity(), IClickable {
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

    private fun initCatListFromApi() {

        mainViewModule.apply {
            catList.observe(this@MainActivity) { response ->
                if (response == null) {
                    return@observe
                }
                catsAdapter = CatsAdapter(response, this@MainActivity)
                recyclerView.adapter = catsAdapter

            }
        }
    }


    override fun onClickListener(key: String, item: String) {

        when (key) {
            id -> {
                startActivity(
                    Intent(this@MainActivity, CatDetailActivity::class.java).apply {
                    putExtra(key, item)
                })
            }

            breed -> {
                startActivity(
                    Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                    putExtra(key, item)
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