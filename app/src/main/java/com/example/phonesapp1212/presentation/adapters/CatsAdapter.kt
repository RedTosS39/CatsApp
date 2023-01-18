package com.example.phonesapp1212.presentation.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.breed
import com.example.phonesapp1212.constants.Constants.deleteBreed
import com.example.phonesapp1212.constants.Constants.id
import com.example.phonesapp1212.constants.Constants.showList
import com.example.phonesapp1212.databinding.ItemListCardviewBinding
import com.example.phonesapp1212.repository.IClickable
import com.squareup.picasso.Picasso

class CatsAdapter(
    mList: List<Breed>,
    private val clickable: IClickable,
    private val favoriteList: Map<String, Boolean>?
) : RecyclerView.Adapter<CatsAdapter.MovieHolder>() {

    private val list: MutableList<Breed> = mList as MutableList<Breed>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cardview, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(list[position], clickable, favoriteList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemListCardviewBinding.bind(view)

        fun bind(item: Breed?, clickable: IClickable, favorite: Map<String, Boolean>?) = with(binding) {

            if (favorite?.containsKey(item?.name) == true)  {
                favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_24_added)
            } else {
                favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            catDetailsBreedTV.text = item?.name
            Picasso.get()
                .load("https://cdn2.thecatapi.com/images/" + item?.reference_image_id + ".jpg")
                .into(catDetailsImageIV)

            favoriteImage.setOnClickListener {

                if(favoriteImage.id == R.drawable.ic_baseline_favorite_24_added ) {
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                    item?.name?.let {
                        clickable.onClickListener(deleteBreed, it)
                    }

                } else {
                    item?.name?.let {
                        clickable.onClickListener(breed, it)
                    }
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_24_added)
                }
            }

            deleteImage.setOnClickListener {
                clickable.onClickListener(showList, "showAll")
            }
        }

        init {
            //tap on current item and send image id
            view.setOnClickListener {
                list[absoluteAdapterPosition].reference_image_id.let {
                    try {
                        clickable.onClickListener(id, it)
                    } catch (e: Exception) {
                        e.toString()
                    }
                    Log.d("pokemon", ":clicked ${list[absoluteAdapterPosition]} ")
                }
            }
        }
    }
}

