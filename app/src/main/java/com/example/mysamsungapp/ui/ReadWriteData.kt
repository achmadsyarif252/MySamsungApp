package com.example.mysamsungapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.health.connect.client.records.*
import androidx.health.connect.client.units.*
import androidx.health.connect.client.units.BloodGlucose
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.adapter.*
import com.example.mysamsungapp.databinding.ActivityReadWriteDataBinding
import com.example.mysamsungapp.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import kotlin.random.Random

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
                    2 -> {
                        writeBasalMetabolicRate(x)
                    }
                    3 -> {
                        writeBloodGlucose(x)
                    }
                    4 -> {
                        writeBloodPressure(x)
                    }
                    5 -> {
                        writeBodyFat(x)
                    }
                    6 -> {
                        writeBodyTemper(x)
                    }
                    7 -> {
                        writeBoneMass(x)
                    }
                    14 -> {
                        writeHeartRate(x)
                    }
                }
                binding.edtHD.text.clear()
            }
        }


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedItem = p2
                when (p0?.getItemAtPosition(p2).toString()) {
                    "Calories Burned" -> {
                        binding.edtHD.hint = "KCal"
                        readActiveCalories()
                    }
                    "Basal Body Temperature" -> {
                        binding.edtHD.hint = "Basal Body Temp"
                        readBasalBodyTemp()
                    }
                    "Basal Metabolic Record" -> {
                        binding.edtHD.hint = "Basal Metabolic Record"
                        readBasalMetabolic()
                    }
                    "Blood Glucose" -> {
                        binding.edtHD.hint = "Blood Glucose"
                        readBloodGlucose()
                    }
                    "Blood Pressure" -> {
                        binding.edtHD.hint = "Systole"
                        readBloodPressure()
                    }
                    "Body Fat" -> {
                        binding.edtHD.hint = "Percentage"
                        readBodyFat()
                    }
                    "Body Temperature" -> {
                        binding.edtHD.hint = "Temperature"
                        readBodyTemper()
                    }
                    "Bone Mass" -> {
                        binding.edtHD.hint = "Bone Mass (KG)"
                        readBoneMass()
                    }

                    "Heart Rate" -> {
                        binding.edtHD.hint = "Heart Rate"
                        readHeartRate()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun writeBoneMass(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBoneMass(
                listOf(
                    BoneMassRecord(
                        Mass.kilograms(x),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readBoneMass()
        }
    }

    private fun readBoneMass() {
        lifecycleScope.launch {
            homeViewModel.readBoneMass(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.boneMass.observe(this@ReadWriteData) {
            binding.rvData.adapter = BoneMassAdapter(it)
        }
    }

    private fun readBodyTemper() {
        lifecycleScope.launch {
            homeViewModel.readBodyTemper(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.bodyTemper.observe(this@ReadWriteData) {
            binding.rvData.adapter = BodyTemperAdapter(it)
        }
    }

    private fun writeBodyTemper(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBodyTemper(
                listOf(
                    BodyTemperatureRecord(
                        Temperature.celsius(x),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readBodyTemper()
        }
    }

    private fun writeBodyFat(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBodyFatRecord(
                listOf(
                    BodyFatRecord(
                        Percentage(x),
                        time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readBodyFat()
        }
    }

    private fun readBodyFat() {
        lifecycleScope.launch {
            homeViewModel.readBodyFatRecord(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.bodyFatRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = BodyFatAdapter(it)
        }
    }

    private fun writeBloodPressure(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBloodPressure(
                listOf(
                    BloodPressureRecord(
                        Pressure.millimetersOfMercury(x),
                        Pressure.millimetersOfMercury(x - 20),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readBloodPressure()
        }
    }

    private fun readBloodPressure() {
        lifecycleScope.launch {
            homeViewModel.readBloodPressure(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.bloodPressureRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = BloodPressureAdapter(it)
        }
    }


    private fun readHeartRate() {
        lifecycleScope.launch {
            homeViewModel.readHeartRecord(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.heartRateRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = HeartRateAdapter(it)
        }
    }

    private fun writeHeartRate(x: Double) {
        val sample = listOf(
            HeartRateRecord.Sample(ZonedDateTime.now().toInstant(), x.toLong())
        )

        lifecycleScope.launch {
            homeViewModel.writeHeartRecord(
                ZonedDateTime.now(), ZonedDateTime.now().plusSeconds(15),
                sample
            )
            readHeartRate()
        }
    }

    private fun readBloodGlucose() {
        lifecycleScope.launch {
            homeViewModel.readBloodGlucose(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.bloodGlucose.observe(this@ReadWriteData) {
            binding.rvData.adapter = BloodGlucoseAdapter(it)
        }
    }

    private fun writeBloodGlucose(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBloodGlucose(
                listOf(
                    BloodGlucoseRecord(
                        BloodGlucose.millimolesPerLiter(x),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readBloodGlucose()
        }
    }

    private fun writeBasalMetabolicRate(x: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeBasalMetabolicRate(
                listOf(
                    BasalMetabolicRateRecord(
                        Power.kilocaloriesPerDay(x), time.toInstant(),
                        time.offset
                    )
                )
            )
            readBasalMetabolic()
        }
    }

    private fun readBasalMetabolic() {
        lifecycleScope.launch {
            homeViewModel.readBasalMetabolicRate(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.basalMetabolicRateRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = BasalMetabolicAdapter(it)
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

    private fun readBasalBodyTemp() {
        lifecycleScope.launch {
            homeViewModel.readBasalBodyTempt(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.bodyBasalBodyTemperatureRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = BodyBasalTemp(it)
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
            readBasalBodyTemp()
        }
    }
}


