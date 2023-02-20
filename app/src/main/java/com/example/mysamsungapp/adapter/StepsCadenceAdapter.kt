package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.records.StepsCadenceRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class StepsCadenceAdapter(private val listData: List<StepsCadenceRecord>) :
    RecyclerView.Adapter<StepsCadenceAdapter.ViewHolder>() {
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
                    Rate : ${listData[position].samples[i].rate} 
                    Time : ${InstantFormatter.formatTime(listData[position].samples[i].time)}
                """.trimIndent()
            }
        }
    }
}