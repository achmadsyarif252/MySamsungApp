package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class TotalCaloriesBurnedAdapter(private val listData: List<TotalCaloriesBurnedRecord>) :
    RecyclerView.Adapter<TotalCaloriesBurnedAdapter.ViewHolder>() {
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
           Total KCal Burned : ${listData[position].energy.inKilocalories}
           Start Type ${InstantFormatter.formatTime(listData[position].startTime)}
           End Type ${InstantFormatter.formatTime(listData[position].endTime)}
       """.trimIndent()
    }
}