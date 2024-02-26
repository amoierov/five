package com.example.five.artworklist.presentation

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.example.five.artworklist.domain.models.Artwork

class ArtworkAdapter(private var artworksList: List<Artwork>): RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>() {
    class ArtworkViewHolder(val composeView: ComposeView) : RecyclerView.ViewHolder(composeView) {
        fun bind(artwork: Artwork) {
            composeView.setContent {
                ArtworkItem(artwork = artwork)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        return ArtworkViewHolder(ComposeView(parent.context))
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