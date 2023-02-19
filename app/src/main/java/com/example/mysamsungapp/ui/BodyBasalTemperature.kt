package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.units.Energy
import androidx.health.connect.client.units.Temperature
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
import com.example.mysamsungapp.adapter.ActiveCaloriesBurnedAdapter
import com.example.mysamsungapp.adapter.BodyBasalTemp
import com.example.mysamsungapp.databinding.ActivityActiveCaloryBurnedBinding
import com.example.mysamsungapp.databinding.ActivityBodyBasalTemperatureBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class BodyBasalTemperature : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityBodyBasalTemperatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyBasalTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        initRV()
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun initRV() {
        lifecycleScope.launch {
            homeViewModel.readBasalBodyTempt(
                ZonedDateTime.now().minusHours(1),
                ZonedDateTime.now()
            )
        }

        binding.rvBodyBasal.layoutManager = LinearLayoutManager(this)
        homeViewModel.bodyBasalBodyTemperatureRecord.observe(this) {
            val adapter = BodyBasalTemp(it)
            binding.rvBodyBasal.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                val time = ZonedDateTime.now()
                val tmp = binding.bbt.text.toString().toDouble()
                lifecycleScope.launch {
                    homeViewModel.writeBasalBodyTemptRecord(
                        listOf(
                            BasalBodyTemperatureRecord(
                                temperature = Temperature.celsius(tmp),
                                time = time.toInstant(),
                                zoneOffset = time.offset
                            )
                        )
                    )

                    initRV()
                    runOnUiThread {
                        Toast.makeText(
                            this@BodyBasalTemperature,
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