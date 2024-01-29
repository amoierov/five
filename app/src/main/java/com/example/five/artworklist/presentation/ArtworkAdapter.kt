package com.example.five.artworklist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.five.R
import com.example.five.databinding.ItemArtworkBinding
import com.example.five.artworklist.domain.models.Artwork

class ArtworkAdapter(private var artworksList: List<Artwork>): RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>() {
    class ArtworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding by viewBinding(ItemArtworkBinding::bind)
        fun bind(artwork: Artwork) {
            binding.nameArtwork.text = artwork.title
            binding.yearArtwork.text = artwork.dateDisplay
            binding.authorArtwork.text = artwork.artistTitle
            Glide.with(itemView.context)
                .load("https://www.artic.edu/iiif/2/${artwork.imageId}/full/843,/0/default.jpg")
                .into(binding.imageArtwork)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artwork, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artworksList.size
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val artwork = artworksList[position]
        holder.bind(artwork)
    }

    fun updateData(newArtworks: List<Artwork>) {
        artworksList = newArtworks
        notifyDataSetChanged()
    }
}