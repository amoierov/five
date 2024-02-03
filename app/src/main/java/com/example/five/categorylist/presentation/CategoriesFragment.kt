package com.example.five.categorylist.presentation

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_category) {
    private val binding by viewBinding(FragmentCategoryBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = binding.categoryList
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        val categoryViewModel: CategoryViewModel by viewModels()
        categoryViewModel.loadCategories()

        binding.textInputEditText.addTextChangedListener { editable ->
            val searchText = editable.toString()
            if (searchText.length >= 2) {
                // Выполнить фильтрацию данных по введенному тексту
                categoryViewModel.filterCategories(searchText)
            } else {
                categoryViewModel.restoreOriginalCategories()
            }
        }

        val categoryAdapter = CategoryAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClick(text: String) {
                val action = CategoriesFragmentDirections.actionMainFragmentToExhibitsFragment(text)
                findNavController().navigate(action)
            }
        })
        recyclerview.adapter = categoryAdapter
        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                categoryAdapter.updateData(categories)
            }
        }
    }
}


