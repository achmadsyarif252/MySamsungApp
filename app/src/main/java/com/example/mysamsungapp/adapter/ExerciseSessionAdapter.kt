package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.BodyTemperatureRecord
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class ExerciseSessionAdapter(private val listData: List<ExerciseSessionRecord>) :
    RecyclerView.Adapter<ExerciseSessionAdapter.ViewHolder>() {
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
           Title : ${listData[position].title} 
           Exercise Type : ${listData[position].exerciseType} 
           Notes : ${listData[position].notes}
           Start Time ${InstantFormatter.formatTime(listData[position].startTime)}
           End Time ${InstantFormatter.formatTime(listData[position].endTime)}
       """.trimIndent()
    }
}