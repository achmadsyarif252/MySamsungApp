package com.example.mysamsungapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mysamsungapp.sdkhealthconnect.HealthDataType
import com.example.mysamsungapp.adapter.HomeActivityAdapter
import com.example.mysamsungapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 2)
        binding.rvHealthData.layoutManager = layoutManager

        val gridAdapter = HomeActivityAdapter(HealthDataType.listHealthDataType)
        gridAdapter.setOnItemClickCallback(object : HomeActivityAdapter.OnItemClickedCallback {
            override fun onItemClicked(i: Int) {
                onClicked(i)
            }
        })
        binding.rvHealthData.adapter = gridAdapter
    }

    private fun onClicked(i: Int) {
        when (i) {
            0 -> startActivity(Intent(this@HomeActivity, ActiveCaloryBurned::class.java))
            14 -> startActivity(Intent(this@HomeActivity, HeartRateReadRecordActivity::class.java))
        }
    }
}