package com.umutcansahin.coinmarketcapapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.coinmarketcapapp.databinding.RecyclerRowLayoutBinding
import com.umutcansahin.coinmarketcapapp.model.home.Data

class HomeRecyclerAdapter(private val linstener: ItemClickListener): RecyclerView.Adapter<HomeRecyclerAdapter.MViewHolder>() {

    private var coins = emptyList<Data>()
    class MViewHolder(private val binding: RecyclerRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemClickListener,coin:Data) {
            binding.onItemClickListener = listener
            binding.coin = coin
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        return MViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(listener = linstener,coins[position])
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    fun setList(newList: List<Data>) {
        coins = newList
        notifyDataSetChanged()
    }
}