package com.example.phonesapp1212.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.data.model.cats.BreedModel
import com.example.phonesapp1212.R
import com.squareup.picasso.Picasso


class ViewPagerAdapter(private val list: BreedModel)
    : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.fragmentImage)

        fun onBind(position: Int) {
            Picasso
                .get()
                .load("https://cdn2.thecatapi.com/images/${list[position].url}")
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_image_collection, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int){
       holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}