package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.MainLayoutBinding

class HomeActivityAdapter(private val listData: List<String>) :
    RecyclerView.Adapter<HomeActivityAdapter.ViewHolder>() {
    private lateinit var onClickCallback: OnItemClickedCallback


    fun setOnItemClickCallback(onItemClickedCallback: OnItemClickedCallback) {
        this.onClickCallback = onItemClickedCallback
    }

    class ViewHolder(val binding: MainLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.btnFeature.text = listData[position]
        holder.binding.btnFeature.setOnClickListener {
            this.onClickCallback.onItemClicked(position)
        }
    }

    interface OnItemClickedCallback {
        fun onItemClicked(i: Int)
    }
}