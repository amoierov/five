package com.example.five.savedartworklist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.five.artworklist.presentation.ArtworkViewModel
import com.example.five.data.local.toArtwork
import com.example.five.savedartworklist.presentation.compose.ArtworkSaved
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtworksSavedFragment : Fragment() {

    private val savedArtworkViewModel: SavedArtworkViewModel by activityViewModels()
    private val artworkViewModel: ArtworkViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val artworksSaved by savedArtworkViewModel.favoriteArtworks.observeAsState(emptyList())
                ArtworkSaved(
                    artworksSaved = artworksSaved.map { it.toArtwork() },
                    findNavController(),
                    artworkViewModel,
                    savedArtworkViewModel
                )
            }
        }
    }
}