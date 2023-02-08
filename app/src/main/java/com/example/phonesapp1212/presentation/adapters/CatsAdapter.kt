package com.example.phonesapp1212.presentation.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.ADD_TO_FAVORITE
import com.example.phonesapp1212.constants.Constants.DELETE_FROM_FAVORITE
import com.example.phonesapp1212.constants.Constants.ID
import com.example.phonesapp1212.databinding.ItemListCardviewBinding
import com.example.phonesapp1212.repository.IClickable
import com.squareup.picasso.Picasso

class CatsAdapter(
    private val iClickable: IClickable
) : ListAdapter<Breed, CatsAdapter.CatHolder>(ItemCallback), View.OnClickListener {

    override fun onClick(v: View?) {
        val breed = v?.tag as Breed
        when (v.id) {
            R.id.favoriteImage -> iClickable.onClickListener(ADD_TO_FAVORITE, breed.name)
            R.id.deleteImage -> iClickable.onClickListener(DELETE_FROM_FAVORITE, breed.name)
            else -> iClickable.onClickListener(ID, breed.reference_image_id)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCardviewBinding.inflate(inflater, parent, false)

        binding.apply {
            favoriteImage.setOnClickListener(this@CatsAdapter)
            deleteImage.setOnClickListener(this@CatsAdapter)
            root.setOnClickListener(this@CatsAdapter)
        }

        return CatHolder(binding)
    }

    //set data by positions
    override fun onBindViewHolder(holder: CatHolder, position: Int) {

        val currentItem = getItem(position)

        with(holder.binding) {


            root.tag = currentItem
            favoriteImage.tag = currentItem
            deleteImage.tag = currentItem
            catDetailsImageIV.tag = currentItem

            if (currentItem.isFavorite) {
                favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_24_added)
            } else {
                favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            Log.d(
                "pokemon",
                "Name: ${getItem(position).name}, is it Favorite? ${currentItem.isFavorite} "
            )

            catDetailsBreedTV.text = getItem(position)?.name
            Picasso.get()
                .load("https://cdn2.thecatapi.com/images/" + currentItem?.reference_image_id + ".jpg")
                .into(catDetailsImageIV)
        }
    }

    class CatHolder(val binding: ItemListCardviewBinding) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.isFavorite == newItem.isFavorite
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }
    }
}

