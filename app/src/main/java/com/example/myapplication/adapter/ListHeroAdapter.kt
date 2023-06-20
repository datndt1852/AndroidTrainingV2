package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.SuperHeroes
import com.google.android.material.textview.MaterialTextView

class ListHeroAdapter : RecyclerView.Adapter<ListHeroAdapter.MyViewHolder>() {
    private val list = SuperHeroes.heroList
    @SuppressLint("NotifyDataSetChanged")
//    fun setData(context: Context){
//        notifyDataSetChanged()
//    }
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: MaterialTextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_hero,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hero = list[position]
        Glide.with(holder.image).load(hero.images).into(holder.image)
        holder.title.text = hero.title
    }

}