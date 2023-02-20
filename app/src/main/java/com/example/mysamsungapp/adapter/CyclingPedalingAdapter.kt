package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.CervicalMucusRecord
import androidx.health.connect.client.records.CyclingPedalingCadenceRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class CyclingPedalingAdapter(private val listData: List<CyclingPedalingCadenceRecord>) :
    RecyclerView.Adapter<CyclingPedalingAdapter.ViewHolder>() {
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
        listData.forEach {
            for (i in it.samples.indices) {
                holder.binding.tvData.text = """
                    Revolution Per Minute : ${listData[position].samples[i].revolutionsPerMinute}
                    Time : ${InstantFormatter.formatTime(listData[position].samples[i].time)}
                """.trimIndent()
            }
        }
    }
}