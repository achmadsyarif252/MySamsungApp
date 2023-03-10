package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.health.connect.client.records.ActiveCaloriesBurnedRecord
import androidx.health.connect.client.units.Energy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
import com.example.mysamsungapp.adapter.ActiveCaloriesBurnedAdapter
import com.example.mysamsungapp.databinding.ActivityActiveCaloryBurnedBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ActiveCaloryBurned : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityActiveCaloryBurnedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActiveCaloryBurnedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        initRV()
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun initRV() {
        lifecycleScope.launch {
            homeViewModel.readActiveCaloriesBurnedRecord(
                ZonedDateTime.now().minusHours(1),
                ZonedDateTime.now()
            )
        }

        binding.rvAcB.layoutManager = LinearLayoutManager(this)
        homeViewModel.activeCaloriesBurnedRecord.observe(this) {
            val adapter = ActiveCaloriesBurnedAdapter(it)
            binding.rvAcB.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                val time = ZonedDateTime.now()
                val energy = Energy.calories(binding.bpm.text.toString().toDouble())
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
                    initRV()
                    runOnUiThread {
                        Toast.makeText(
                            this@ActiveCaloryBurned,
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