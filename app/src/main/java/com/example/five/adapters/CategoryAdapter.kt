package com.example.five.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.databinding.ItemCategoryBinding

class CategoryAdapter(private val categoryList: List<String>, private val onClick: () -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding by viewBinding(ItemCategoryBinding::bind)
        fun bind(category: String, onClick: () -> Unit) {
            binding.textCategory.text = category
            binding.arrow.setOnClickListener {
                onClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category) {onClick()}
    }


}