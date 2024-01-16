package com.example.five.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.databinding.ItemArtworkBinding
import com.example.five.models.Artwork

class ArtworkAdapter(private val artworksList: List<Artwork>): RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>() {
    class ArtworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding by viewBinding(ItemArtworkBinding::bind)
        fun bind(artworksList: List<Artwork>) {
            binding.nameArtwork.text =  artworksList[0].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}