package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.adapter.InstantFormatter.formatTime
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


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
           Start Type ${formatTime(listData[position].startTime)}
           End Type ${formatTime(listData[position].endTime)}
       """.trimIndent()
    }
}