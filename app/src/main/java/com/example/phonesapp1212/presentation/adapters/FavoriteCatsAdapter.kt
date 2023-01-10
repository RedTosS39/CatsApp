package com.example.phonesapp1212.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.room.model.CatEntity
import androidx.recyclerview.widget.ListAdapter
import com.example.phonesapp1212.R

class FavoriteCatsAdapter : ListAdapter<CatEntity, FavoriteCatsAdapter.FavoriteCatsViewHolder>(CatsComparator()) {

    class FavoriteCatsViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        private val titleItemView: TextView = itemView.findViewById(R.id.favorite_breed)

        fun bind(title: String?) {
            titleItemView.text = title
        }

        companion object {
            fun create(parent: ViewGroup) : FavoriteCatsViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_cardview, parent, false)
                return FavoriteCatsViewHolder(view)
            }
        }
    }

    class CatsComparator : DiffUtil.ItemCallback<CatEntity>() {
        override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCatsViewHolder {
        return FavoriteCatsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteCatsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title)
    }
}