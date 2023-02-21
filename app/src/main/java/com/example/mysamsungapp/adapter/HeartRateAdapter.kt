package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.HeartRateRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.adapter.InstantFormatter.formatTime
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class HeartRateAdapter(private val listData: List<HeartRateRecord>) :
    RecyclerView.Adapter<HeartRateAdapter.ViewHolder>() {
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
        for (i in 0 until listData[position].samples.size) {
            holder.binding.tvData.text = holder.binding.tvData.text.toString() + """
                Heart Rate ${listData[position].samples[i].beatsPerMinute} BPM
                Time :${InstantFormatter.formatTime(listData[position].samples[i].time)}
            """.trimIndent()
        }

    }


}