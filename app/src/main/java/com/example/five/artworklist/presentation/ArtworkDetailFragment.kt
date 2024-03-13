package com.example.five.artworklist.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.five.R
import com.example.five.databinding.FragmentArtworkDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {
    private val binding by viewBinding(FragmentArtworkDetailBinding::bind)
    private val args: ArtworkDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artwork = args.selectedArtwork

        binding.nameArtwork.text = artwork.title
        binding.yearArtwork.text = artwork.dateDisplay
        binding.authorArtwork.text = artwork.artistTitle
        binding.typeArtwork.text = artwork.artworkTypeTitle
        binding.materialsArtwork.text = artwork.materialTitles.toString()
        binding.sizesArtwork.text = artwork.dimensions
        binding.galleryArtwork.text = artwork.galleryTittle
        binding.creditsArtwork.text = artwork.creditLine
        Glide.with(requireContext())
            .load("https://www.artic.edu/iiif/2/${artwork.imageId}/full/843,/0/default.jpg")
            .into(binding.bigImageArtwork)

        binding.menuBarText.text = artwork.title
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}