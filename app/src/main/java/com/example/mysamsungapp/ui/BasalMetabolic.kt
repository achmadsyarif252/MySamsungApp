package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.health.connect.client.records.BasalMetabolicRateRecord
import androidx.health.connect.client.units.Power
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
import com.example.mysamsungapp.adapter.BasalMetabolicAdapter
import com.example.mysamsungapp.databinding.ActivityBasalMetabolicBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class BasalMetabolic : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityBasalMetabolicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasalMetabolicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        initRV()
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun initRV() {
        lifecycleScope.launch {
            homeViewModel.readBasalMetabolicRate(
                ZonedDateTime.now().minusHours(1),
                ZonedDateTime.now()
            )
        }

        binding.rvBasalMetabolic.layoutManager = LinearLayoutManager(this)
        homeViewModel.basalMetabolicRateRecord.observe(this) {
            val adapter = BasalMetabolicAdapter(it)
            binding.rvBasalMetabolic.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                val time = ZonedDateTime.now()
                val wats = binding.bbt.text.toString().toDouble()
                lifecycleScope.launch {
                    homeViewModel.writeBasalMetabolicRate(
                        listOf(
                            BasalMetabolicRateRecord(
                                time = time.toInstant(),
                                zoneOffset = time.offset,
                                basalMetabolicRate = Power.watts(wats)// ada 2 jenis,kilokaloridat,watt
                            )
                        )
                    )

                    initRV()
                    runOnUiThread {
                        Toast.makeText(
                            this@BasalMetabolic,
                            "Success Insert Record",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                binding.bbt.text.clear()
            }
        }
    }
}