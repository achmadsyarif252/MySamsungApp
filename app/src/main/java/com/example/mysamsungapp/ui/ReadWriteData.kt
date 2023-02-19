package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.records.BasalBodyTemperatureRecord
import androidx.health.connect.client.units.Energy
import androidx.health.connect.client.units.Temperature
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.adapter.ActiveCaloriesBurnedAdapter
import com.example.mysamsungapp.adapter.BodyBasalTemp
import com.example.mysamsungapp.databinding.ActivityReadWriteDataBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ReadWriteData : AppCompatActivity() {
    private lateinit var binding: ActivityReadWriteDataBinding
    private lateinit var homeViewModel: HomeViewModel
    var selectedItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadWriteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        binding.rvData.layoutManager = LinearLayoutManager(this)

        binding.btnSubmit.setOnClickListener {
            val value = binding.edtHD.text.toString()
            if (value.isEmpty()) binding.edtHD.error = "Should not empty"
            else {
                val x = value.toDouble()
                when (selectedItem) {
                    0 -> {
                        writeActiveCalorie(x)
                    }
                    1 -> {
                        writeBodyBasalTemp(x)
                    }
                }
                binding.edtHD.text.clear()
            }

        }


        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedItem = p2
                when (p0?.getItemAtPosition(p2).toString()) {
                    "Calories Burned" -> {
                        binding.edtHD.hint = "KCal"
                        readActiveCalories()
                    }
                    "Basal Body Temperature" -> {
                        binding.edtHD.hint = "Basal Body Temp"
                        lifecycleScope.launch {
                            homeViewModel.readBasalBodyTempt(
                                ZonedDateTime.now().minusDays(7),
                                ZonedDateTime.now()
                            )
                        }
                        homeViewModel.bodyBasalBodyTemperatureRecord.observe(this@ReadWriteData) {
                            binding.rvData.adapter = BodyBasalTemp(it)
                        }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedItem = p2
                when (p0?.getItemAtPosition(p2).toString()) {
                    "Calories Burned" ->
                        binding.edtHD.hint = "KCal"
                    "Basal Body Temperature" ->
                        binding.edtHD.hint = "Basal Body Temp"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun readActiveCalories() {
        lifecycleScope.launch {
            homeViewModel.readActiveCaloriesBurnedRecord(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.activeCaloriesBurnedRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = ActiveCaloriesBurnedAdapter(it)
        }
    }

    private fun writeActiveCalorie(value: Double) {
        val time = ZonedDateTime.now()
        val energy = Energy.calories(value)
        lifecycleScope.launch {
            homeViewModel.writeActiveCaloriesBurnedRecord(
                listOf(
                    ActiveCaloriesBurnedRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusSeconds(15).toInstant(),
                        energy = energy,
                        startZoneOffset = time.offset,
                        endZoneOffset = time.offset,
                    )
                )
            )
            readActiveCalories()
        }
    }

    private fun writeBodyBasalTemp(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBasalBodyTemptRecord(
                listOf(
                    BasalBodyTemperatureRecord(
                        temperature = Temperature.celsius(value),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
        }
    }
}


