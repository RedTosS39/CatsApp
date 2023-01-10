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
import com.example.phonesapp1212.repository.IClickable
import com.squareup.picasso.Picasso

class CatsAdapter(
    mList: List<Breed>,
    private val clickable: IClickable
) :
    RecyclerView.Adapter<CatsAdapter.MovieHolder>() {

    private val list: MutableList<Breed> = mList as MutableList<Breed>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cardview, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieHolder(movieViewHolder: View) : RecyclerView.ViewHolder(movieViewHolder) {
        private val text: TextView = movieViewHolder.findViewById(R.id.catDetailsBreedTV)
        private val breedImage: ImageView = movieViewHolder.findViewById(R.id.catDetailsImageIV)
        private val favorite: ImageView = movieViewHolder.findViewById(R.id.favoriteImage)
        private val deleteImage: ImageView = movieViewHolder.findViewById(R.id.deleteImage)

        fun bind(item: Breed?) {
            text.text = item?.name
            Picasso
                .get()
                .load("https://cdn2.thecatapi.com/images/" + item?.reference_image_id + ".jpg")
                .into(breedImage)

        }

        init {
            movieViewHolder.setOnClickListener {
                list[absoluteAdapterPosition].reference_image_id.let {
                    try {
                        clickable.onClickListener(it)

                    } catch (e: Exception) {
                        e.toString()

                    }
                    Log.d("pokemon", ":clicked ${list[absoluteAdapterPosition]} ")
                }
            }
        }
//          private fun deleteItem(position: Int) {
//               list.removeAt(position)
//               notifyDataSetChanged()
//          }

        private fun changeImage(position: Int) {
            list[position].let {
                favorite.setImageResource(R.drawable.ic_baseline_favorite_24_added)
            }
            notifyDataSetChanged()
        }
    }
}

