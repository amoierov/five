package com.example.five.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.adapters.CategoryAdapter
import com.example.five.databinding.FragmentMainBinding


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
        recyclerview.adapter = CategoryAdapter(listOf("Картины","Вазы","Инструменты","Картины")) {findNavController().navigate(R.id.action_yourFragment_to_exhibitsFragment)}
    }
}