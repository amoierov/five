package com.example.five.categorylist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.five.R
import com.example.five.databinding.ItemCategoryBinding
import com.example.five.categorylist.domain.models.ArtworkType

class CategoryAdapter(private var categoryList: List<ArtworkType>, private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding by viewBinding(ItemCategoryBinding::bind)
        fun bind(category: ArtworkType, onItemClickListener: OnItemClickListener) {
            binding.textCategory.text = category.title
            binding.category.setOnClickListener {
                onItemClickListener.onItemClick(category.title)
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
        holder.bind(category, onItemClickListener)
    }

    fun updateData(newCategories: List<ArtworkType>) {
        categoryList = newCategories
        notifyDataSetChanged()
    }
}