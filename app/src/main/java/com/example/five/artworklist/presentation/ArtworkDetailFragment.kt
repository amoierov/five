package com.example.five.artworklist.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.databinding.FragmentArtworkDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtworkDetailFragment : Fragment(R.layout.fragment_artwork_detail) {
    private val binding by viewBinding(FragmentArtworkDetailBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}