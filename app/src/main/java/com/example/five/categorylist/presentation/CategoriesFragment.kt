package com.example.five.categorylist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.artworklist.presentation.ArtworkViewModel
import com.example.five.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_category) {
    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private val artworkViewModel: ArtworkViewModel by activityViewModels()
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

        binding.imageSaved.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_artworkSavedFragment)
        }

        val categoryAdapter = CategoryAdapter(emptyList(), object : OnItemClickListener {
            override fun onItemClick(text: String) {
               val action = CategoriesFragmentDirections.actionCategoriesFragmentToArtworksFragment(text)
                findNavController().navigate(action)
            }
        })
        recyclerview.adapter = categoryAdapter
        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                categoryAdapter.updateData(categories)
            }
        }

        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Проверяем, содержит ли поле ввода текст
                if (s.isNullOrEmpty()) {
                    // Если текст отсутствует, показываем ImageView
                    binding.imageViewSearch.visibility = View.VISIBLE
                } else {
                    // Если есть текст, скрываем ImageView
                    binding.imageViewSearch.visibility = View.GONE
                }
            }
            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }
}


