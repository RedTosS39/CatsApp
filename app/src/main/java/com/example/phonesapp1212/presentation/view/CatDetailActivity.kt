package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.data.model.cats.BreedModel
import com.example.data.model.cats.CatBreedDetails
import com.example.phonesapp1212.R
import com.example.phonesapp1212.presentation.adapters.ViewPagerAdapter
import com.example.phonesapp1212.presentation.viewmodel.CatDetailViewModel
import com.example.phonesapp1212.presentation.viewmodel.CurrentCatViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatDetailActivity : AppCompatActivity() {
    private lateinit var catBreedDetailsTitle: TextView
    private lateinit var catDetailsTV: TextView
    private lateinit var catImageDetails: ImageView
    private lateinit var viewPager2: ViewPager2
    private val viewModel: CatDetailViewModel by lazy { ViewModelProvider(this)[CatDetailViewModel::class.java] }
    private val currentCatViewModel: CurrentCatViewModel by lazy { ViewModelProvider(this)[CurrentCatViewModel::class.java] }

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
                currentCatViewModel.getCatInfo(response.breeds[0].id)
            }
        }

        currentCatViewModel.apply {
            liveData.observe(this@CatDetailActivity) { response ->
                if (response == null) {
                    return@observe
                }
                getBreedModel(response)
            }
        }
    }

    private fun getCatsDetails(catBreedDetails: CatBreedDetails) {

        catBreedDetails.breeds.let {
            catBreedDetailsTitle.text = it[0].name
            catDetailsTV.text = it[0].description
        }
        Picasso.get().load(catBreedDetails.url).into(catImageDetails)
    }

    private fun getBreedModel(breedModel: BreedModel) {

        val adapter = ViewPagerAdapter(breedModel)
        viewPager2.adapter = adapter
    }

    private fun initViews() {
        catBreedDetailsTitle = findViewById(R.id.catBreedDetailsTitle)
        catDetailsTV = findViewById(R.id.catDetailsTV)
        catImageDetails = findViewById(R.id.catBreedDetailsImage)
        viewPager2 = findViewById(R.id.viewPager2)
    }
}