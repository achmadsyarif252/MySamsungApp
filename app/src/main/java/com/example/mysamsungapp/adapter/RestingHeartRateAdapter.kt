package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.RestingHeartRateRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding
import kotlin.time.Duration.Companion.minutes

class RestingHeartRateAdapter(private val listData: List<RestingHeartRateRecord>) :
    RecyclerView.Adapter<RestingHeartRateAdapter.ViewHolder>() {
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
           BPM : ${listData[position].beatsPerMinute.minutes} 
           Date : ${InstantFormatter.formatTime(listData[position].time)}
       """.trimIndent()
    }
}