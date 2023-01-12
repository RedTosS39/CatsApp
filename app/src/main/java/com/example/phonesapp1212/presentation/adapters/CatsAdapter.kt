package com.example.phonesapp1212.presentation.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.web.model.cats.Breed
import com.example.phonesapp1212.R
import com.example.phonesapp1212.constants.Constants.breed
import com.example.phonesapp1212.constants.Constants.id
import com.example.phonesapp1212.databinding.ItemListCardviewBinding
import com.example.phonesapp1212.repository.IClickable
import com.squareup.picasso.Picasso

class CatsAdapter(
    mList: List<Breed>,
    private val clickable: IClickable
) : RecyclerView.Adapter<CatsAdapter.MovieHolder>() {

    private val list: MutableList<Breed> = mList as MutableList<Breed>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cardview, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(list[position], clickable)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemListCardviewBinding.bind(view)

        fun bind(item: Breed?, clickable: IClickable) = with(binding) {
            catDetailsBreedTV.text = item?.name
            Picasso.get().load("https://cdn2.thecatapi.com/images/" + item?.reference_image_id + ".jpg")
                .into(catDetailsImageIV)

            favoriteImage.setOnClickListener {
                item?.name?.let {
                    clickable.onClickListener(breed, it)
                }
            }
        }

        init {
            //tap on current item and send image id
            view.setOnClickListener {
                list[absoluteAdapterPosition].reference_image_id.let {
                    try {
                        clickable.onClickListener(id, it )
                    } catch (e: Exception) {
                        e.toString()
                    }
                    Log.d("pokemon", ":clicked ${list[absoluteAdapterPosition]} ")
                }
            }
        }
    }
    

}

