package com.example.healthcare.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthcare2.Domain.CategoryModel
import com.example.healthcare2.R
import com.example.healthcare2.databinding.ViewholderCategoryBinding

class CategoryAdapter (val items:MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    private lateinit var context: Context
    inner class Viewholder (val binding: ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item =items[position]
        holder.binding.titleTxt.text=item.Name

        Glide.with(context)
            .load(item.Picture)
            

            .into(holder.binding.img1)

    }

    override fun getItemCount(): Int =items.size
}
