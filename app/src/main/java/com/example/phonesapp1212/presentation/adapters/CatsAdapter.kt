package com.example.phonesapp1212.presentation.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ADD_TO_FAVORITE
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.constants.Constants.SHOW_SAVED
import com.example.phonesapp1212.databinding.ItemListCardviewBinding
import com.example.phonesapp1212.repository.IClickable
import com.squareup.picasso.Picasso

class CatsAdapter(
    private val iClickable: IClickable
) : ListAdapter<Breed, CatsAdapter.MovieHolder>(ItemCallback), View.OnClickListener {

    //private val list: MutableList<Breed> = mList as MutableList<Breed>

    override fun onClick(v: View?) {
        val breed = v?.tag as Breed
        when (v.id) {
            R.id.favoriteImage -> iClickable.onClickListener(ADD_TO_FAVORITE, breed.name)
            R.id.deleteImage -> iClickable.onClickListener(SHOW_SAVED, "showAll")
            else -> iClickable.onClickListener(ID, breed.reference_image_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemListCardviewBinding.inflate(view, parent, false)

        binding.apply {
            favoriteImage.setOnClickListener(this@CatsAdapter)
            deleteImage.setOnClickListener(this@CatsAdapter)
            root.setOnClickListener(this@CatsAdapter)
        }

        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        //get current element on list
        val breed = getItem(position)

        with(holder.binding) {
            root.tag = breed
            favoriteImage.tag = breed
            deleteImage.tag = breed
            catDetailsImageIV.tag = breed

            favoriteImage.setImageResource(
                if (breed.isFavorite) {
                    R.drawable.ic_baseline_favorite_24_added
                } else {
                    R.drawable.ic_baseline_favorite_border_24
                }
            )

            catDetailsBreedTV.text = breed?.name
            Picasso.get()
                .load("https://cdn2.thecatapi.com/images/" + breed?.reference_image_id + ".jpg")
                .into(catDetailsImageIV)

        }

    }

    inner class MovieHolder(val binding: ItemListCardviewBinding) : RecyclerView.ViewHolder(binding.root) {

        //fun bind(item: Breed?, clickable: IClickable, favorite: Map<String, Boolean>?) =
    }

    object ItemCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }

    }
}

