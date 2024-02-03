package com.example.five.artworklist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.databinding.FragmentArtworkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtworksFragment : Fragment(R.layout.fragment_artwork) {

    private val binding by viewBinding(FragmentArtworkBinding::bind)
    private val args: ArtworksFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_exhibitsFragment_to_yourFragment)
        }

        val recyclerView = binding.artworkList
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val artworkViewModel: ArtworkViewModel by viewModels()

        binding.menuBarText.text = args.selectedCategoryTitle

        artworkViewModel.loadArtworks(args.selectedCategoryTitle)

        binding.textInputEditText.addTextChangedListener { editable ->
            val searchText = editable.toString()
            if (searchText.length >= 2) {
                // Выполнить фильтрацию данных по введенному тексту
                artworkViewModel.filterArtworks(searchText)
            } else {
                artworkViewModel.restoreOriginalArtworks()
            }
        }

        val artworkAdapter = ArtworkAdapter(emptyList())
        recyclerView.adapter = artworkAdapter

        artworkViewModel.artworks.observe(viewLifecycleOwner) { artworks ->
            if (artworks != null) {
                artworkAdapter.updateData(artworks)
            }
        }
    }
}