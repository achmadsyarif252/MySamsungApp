package com.example.mysamsungapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.health.connect.client.records.BloodGlucoseRecord
import androidx.recyclerview.widget.RecyclerView
import com.example.mysamsungapp.databinding.HeartRateLayoutBinding

class BloodGlucoseAdapter(private val listData: List<BloodGlucoseRecord>) :
    RecyclerView.Adapter<BloodGlucoseAdapter.ViewHolder>() {
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
           Blood Glucose : ${listData[position].level.inMillimolesPerLiter} mmOl
           Time ${InstantFormatter.formatTime(listData[position].time)}
       """.trimIndent()
    }
}