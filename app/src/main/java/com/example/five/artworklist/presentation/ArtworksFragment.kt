package com.example.five.artworklist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.five.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArtworksFragment : Fragment(R.layout.fragment_artwork) {

    private val args: ArtworksFragmentArgs by navArgs()
    private val artworkViewModel: ArtworkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val nameCategory = args.selectedCategoryTitle
        artworkViewModel.loadArtworks(nameCategory)
        return ComposeView(requireContext()).apply {
            setContent {
                val artworks by artworkViewModel.artworks.observeAsState(emptyList())
                artworks?.let { ArtworkScreen(artworks = it, nameCategory, findNavController(), artworkViewModel) }
            }
        }
    }
}