package com.example.five.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.presentation.adapters.ArtworkAdapter
import com.example.five.databinding.FragmentArtworkBinding
import com.example.five.presentation.viewmodel.ArtworkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExhibitsFragment : Fragment() {

    private val binding by viewBinding(FragmentArtworkBinding::bind)
    private val args: ExhibitsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artwork, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_exhibitsFragment_to_yourFragment)
        }

        val recyclerView = binding.artworkList
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val artworkViewModel: ArtworkViewModel by viewModels()


        artworkViewModel.loadArtworks(args.selectedCategoryTitle)


        val artworkAdapter = ArtworkAdapter(emptyList())
        recyclerView.adapter = artworkAdapter

        artworkViewModel.artworks.observe(viewLifecycleOwner) { artworks ->
            artworkAdapter.updateData(artworks)
        }
    }
}