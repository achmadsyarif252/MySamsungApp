package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.health.connect.client.records.HeartRateRecord
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
import com.example.mysamsungapp.adapter.HeartRateAdapter
import com.example.mysamsungapp.databinding.ActivityHeartRateReadRecordBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import kotlin.random.Random

class HeartRateReadRecordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHeartRateReadRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeartRateReadRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        initRV()
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun initRV() {
        lifecycleScope.launch {
            homeViewModel.readHeartRecord(ZonedDateTime.now().minusDays(7), ZonedDateTime.now())
        }

        binding.rvHR.layoutManager = LinearLayoutManager(this)
        homeViewModel.heartRateRecord.observe(this) {
            val adapter = HeartRateAdapter(it)
            binding.rvHR.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                val bpm: Long = binding.bpm.text.toString().toInt() + Random.nextInt(10).toLong()
                val sample = listOf(
                    HeartRateRecord.Sample(ZonedDateTime.now().toInstant(), bpm)
                )

                lifecycleScope.launch {
                    homeViewModel.writeHeartRecord(
                        ZonedDateTime.now(), ZonedDateTime.now().plusSeconds(15),
                        sample
                    )
                    initRV()
                    runOnUiThread {
                        Toast.makeText(
                            this@HeartRateReadRecordActivity,
                            "Success Insert Record",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                binding.bpm.text.clear()
            }
        }
    }
}