package com.example.healthcare2.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.healthcare2.Activity.DetailActivity
import com.example.healthcare2.Domain.DoctorModels
import com.example.healthcare2.databinding.ViewholderTopDoctor2Binding
import com.example.healthcare2.databinding.ViewholderTopDoctorBinding

class TopDoctorAdapter2(val items:MutableList<DoctorModels>):
    RecyclerView.Adapter<TopDoctorAdapter2.Viewholder> (){
    private var context:Context?=null
    class Viewholder(val binding: ViewholderTopDoctor2Binding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorAdapter2.Viewholder {
        context=parent.context
        val binding=ViewholderTopDoctor2Binding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }



    override fun onBindViewHolder(holder: TopDoctorAdapter2.Viewholder, position: Int) {
       holder.binding.nameTxt.text=items[position].Name
        holder.binding.specialTxt.text=items[position].Special
        holder.binding.scoreTxt.text=items[position].Rating.toString()
        holder.binding.ratingBar.rating=items[position].Rating.toFloat()
        holder.binding.scoreTxt.text=items[position].Rating.toString()
        holder.binding.degreeTxt.text="Professional Doctor"

        Glide.with(holder.itemView.context)
            .load(items[position].Picture)
            .apply{RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.img2)

        holder.binding.makeBtn.setOnClickListener{
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context?.startActivity(intent)
        }


    }


    override fun getItemCount(): Int =items.size

    }

