package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.cats.CatBreedDetails
import com.example.phonesapp1212.R
import com.example.phonesapp1212.presentation.viewmodel.CatDetailViewModel
import com.squareup.picasso.Picasso

class CatDetailActivity : AppCompatActivity() {
    private lateinit var catBreedDetailsTitle: TextView
    private lateinit var catDetailsTV: TextView
    private lateinit var catImageDetails: ImageView
    private val viewModel: CatDetailViewModel by lazy { ViewModelProvider(this)[CatDetailViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail)

        val id = intent.getStringExtra("id")
        viewModel.getCatInfo(id!!)
        initViews()
        getResult()
    }

    private fun getResult() {
        viewModel.apply {
            liveData.observe(this@CatDetailActivity) { response ->
                if (response == null) {
                    return@observe
                }
                getCatsDetails(response)
            }
        }
    }

    private fun getCatsDetails(catBreedDetails: CatBreedDetails) {

        catBreedDetails.breeds.let {
            catBreedDetailsTitle.text = it[0].id
            catDetailsTV.text = it[0].description
            Picasso
                .get()
                .load("https://cdn2.thecatapi.com/images/${catBreedDetails.id}.jpg")
                .into(catImageDetails)
            }
    }

    private fun initViews() {
        catBreedDetailsTitle = findViewById(R.id.catBreedDetailsTitle)
        catDetailsTV = findViewById(R.id.catDetailsTV)
        catImageDetails = findViewById(R.id.catBreedDetailsImage)
    }
}