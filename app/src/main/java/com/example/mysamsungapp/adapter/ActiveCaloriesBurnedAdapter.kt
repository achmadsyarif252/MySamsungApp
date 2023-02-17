package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding


class ActiveCaloriesBurnedAdapter(private val listData: List<ActiveCaloriesBurnedRecord>) :
    RecyclerView.Adapter<ActiveCaloriesBurnedAdapter.ViewHolder>() {
    class ViewHolder(val binding: HeartRateLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HeartRateLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvData.text = """
           KCal : ${listData[position].energy}
           Start Type ${listData[position].startTime}
           End Type ${listData[position].endTime}
       """.trimIndent()
    }
}