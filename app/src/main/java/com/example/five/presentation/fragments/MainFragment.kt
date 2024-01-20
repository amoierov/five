package com.example.five.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.presentation.adapters.CategoryAdapter
import com.example.five.databinding.FragmentMainBinding
import com.example.five.presentation.interfaces.OnItemClickListener
import com.example.five.presentation.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = binding.categoryList
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        val categoryViewModel: CategoryViewModel by viewModels()
        categoryViewModel.loadCategories()

        val categoryAdapter = CategoryAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClick(categoryTitle: String) {
                val action = MainFragmentDirections.actionMainFragmentToExhibitsFragment(categoryTitle)
                findNavController().navigate(action)
            }
        })
        recyclerview.adapter = categoryAdapter

        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.updateData(categories)
        }
    }
}


