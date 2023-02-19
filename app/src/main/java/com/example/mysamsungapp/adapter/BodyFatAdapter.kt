package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.records.BodyFatRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class BodyFatAdapter(private val listData: List<BodyFatRecord>) :
    RecyclerView.Adapter<BodyFatAdapter.ViewHolder>() {
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
           Percentage : ${listData[position].percentage.value} %
           Time ${InstantFormatter.formatTime(listData[position].time)}
       """.trimIndent()
    }
}