package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.health.connect.client.records.BasalMetabolicRateRecord
import androidx.health.connect.client.records.BloodGlucoseRecord
import androidx.health.connect.client.units.BloodGlucose
import androidx.health.connect.client.units.Power
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
import com.example.mysamsungapp.adapter.BasalMetabolicAdapter
import com.example.mysamsungapp.adapter.BloodGlucoseAdapter
import com.example.mysamsungapp.databinding.ActivityBasalMetabolicBinding
import com.example.mysamsungapp.databinding.ActivityBloodGlucoseBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class BloodGlucose : AppCompatActivity(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityBloodGlucoseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBloodGlucoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        initRV()
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun initRV() {
        lifecycleScope.launch {
            homeViewModel.readBloodGlucose(
                ZonedDateTime.now().minusHours(1),
                ZonedDateTime.now()
            )
        }

        binding.rvBloodGlucose.layoutManager = LinearLayoutManager(this)
        homeViewModel.bloodGlucose.observe(this) {
            val adapter = BloodGlucoseAdapter(it)
            binding.rvBloodGlucose.adapter = adapter
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                val time = ZonedDateTime.now()
                val mmOl = binding.bg.text.toString().toDouble()
                lifecycleScope.launch {
                    homeViewModel.writeBloodGlucose(
                        listOf(
                            BloodGlucoseRecord(
                                time = time.toInstant(),
                                zoneOffset = time.offset,
                                level = BloodGlucose.millimolesPerLiter(mmOl)
                            )
                        )
                    )

                    initRV()
                    runOnUiThread {
                        Toast.makeText(
                            this@BloodGlucose,
                            "Success Insert Record",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                binding.bg.text.clear()
            }
        }
    }
}