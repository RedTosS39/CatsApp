package com.example.phonesapp1212.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.data.web.model.cats.CatBreedDetails
import com.example.data.web.repository.CatBreedRepository
import com.example.data.web.repository.Repository
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.presentation.adapters.ViewPagerAdapter
import com.example.phonesapp1212.presentation.viewmodel.*
import javax.inject.Inject
import kotlin.math.log

class CatDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CatDetailViewModelFactory

    private lateinit var catBreedDetailsTitle: TextView
    private lateinit var catDetailsTV: TextView
    private lateinit var viewPager2: ViewPager2
    private val viewModel: CatDetailViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_detail)

        appComponent.inject(this)

        val id = intent.getStringExtra(ID)
        viewModel.getCatInfo(id!!)
        initViews()
        getResult()

    }

    private fun getResult() {

        viewModel.apply {

            getCatInfoLiveData.observe(this@CatDetailActivity) {
                it?.let {
                    getCatsDetails(it)
                }
                viewModel.getCatIdInfo(it.breeds[0].id)
                Log.d("pokemon", "it.breeds[0].id: ${it.breeds[0].id}:")
            }

            liveData.observe(this@CatDetailActivity) {
                it?.let {
                    getBreedModel(it)
                    Log.d("pokemon", "it $it")
                }
            }
        }
    }

    private fun getCatsDetails(catBreedDetails: CatBreedDetails) {
        catBreedDetails.breeds[0].let {
            catBreedDetailsTitle.text = it.name
            catDetailsTV.text = it.description
        }
    }

    private fun getBreedModel(catBreedDetails: List<CatBreedDetails>) {
        val adapter = ViewPagerAdapter(catBreedDetails)
        viewPager2.adapter = adapter
    }

    private fun initViews() {
        catBreedDetailsTitle = findViewById(R.id.catBreedDetailsTitle)
        catDetailsTV = findViewById(R.id.catDetailsTV)
        viewPager2 = findViewById(R.id.viewPager2)
    }
}