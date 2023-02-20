package com.example.mysamsungapp.ui

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.health.connect.client.records.*
import androidx.health.connect.client.units.*
import androidx.health.connect.client.units.BloodGlucose
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.R
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
    override fun onResume() {
        super.onResume()
        homeViewModel.isHcAvailable()
        homeViewModel.isHCInstalled.observe(this) {
            if (it) {
                Toast.makeText(this@ReadWriteData, "Already Installed", Toast.LENGTH_SHORT)
                    .show()
                binding.rvData.visibility = View.VISIBLE
                binding.edtHD.visibility = View.VISIBLE
                binding.spinner.visibility = View.VISIBLE
                binding.btnSubmit.visibility = View.VISIBLE
                binding.hcmissing.visibility = View.GONE
            } else {
                Toast.makeText(this@ReadWriteData, "Not Installed", Toast.LENGTH_SHORT)
                    .show()
                binding.hcmissing.visibility = View.VISIBLE
                binding.rvData.visibility = View.GONE
                binding.edtHD.visibility = View.GONE
                binding.spinner.visibility = View.GONE
                binding.btnSubmit.visibility = View.GONE
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadWriteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.initContext(this)

        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.hcmissing.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(getString(R.string.market_url) + "?id=" + getString(R.string.health_connect_package))
            startActivity(intent)
        }

        binding.btnSubmit.setOnClickListener {
            val value = binding.edtHD.text.toString()
            if (value.isEmpty()) binding.edtHD.error = "Should not empty"
            else {
                when (selectedItem) {
                    0 -> {
                        writeActiveCalorie(value.toDouble())
                    }
                    1 -> {
                        writeBodyBasalTemp(value.toDouble())
                    }
                    2 -> {
                        writeBasalMetabolicRate(value.toDouble())
                    }
                    3 -> {
                        writeBloodGlucose(value.toDouble())
                    }
                    4 -> {
                        writeBloodPressure(value.toDouble())
                    }
                    5 -> {
                        writeBodyFat(value.toDouble())
                    }
                    6 -> {
                        writeBodyTemper(value.toDouble())
                    }
                    7 -> {
                        writeBoneMass(value.toDouble())
                    }
                    8 -> {
                        writeCervicalMucus(value)
                    }
                    9 -> {
                        writeCyclingPedaling(value.toDouble())
                    }
                    10 -> {
                        writeDistance(value.toDouble())
                    }
                    11 -> {
                        writeElevationGained(value.toDouble())
                    }
                    12 -> {
                        writeExerciseSession(value)
                    }
                    13 -> {
                        writeFloorsClimbed(value.toDouble())
                    }
                    14 -> {
                        writeHeartRate(value.toDouble())
                    }
                    15 -> {
                        writeHeight(value.toDouble())
                    }
                    16 -> {
                        writeHydration(value.toDouble())
                    }
                    17 -> {
                        writeLeanBodyMass(value.toDouble())
                    }
                    18 -> {
                        writeMenstruationFlow(value)
                    }
                    19 -> {
                        writeNutrition(value)
                    }
                    20 -> {
                        writeOvulation(value)
                    }
                    21 -> {
                        writeOxygenSaturation(value.toDouble())
                    }
                    22 -> {
                        writePower(value.toDouble())
                    }
                    23 -> {
                        writeRespiration(value.toDouble())
                    }
                    24 -> {
                        writeRestingHeartRate(value.toLong())
                    }
                    25 -> {
                        writeSexualActivity(value)
                    }
                    26 -> {
                        writeSleepSession(value)
                    }
                    27 -> {
                        writeSleepStage(value)
                    }
                    28 -> {
                        writeSpeed(value.toDouble())
                    }
                    29 -> {
                        writeStepsCadence(value.toDouble())
                    }
                    30 -> {
                        writeSteps(value.toLong())
                    }
                    31 -> {
                        writeTotalCaloriesBurned(value.toDouble())
                    }
                    32 -> {
                        writeVo2Max(value.toDouble())
                    }
                    33 -> {
                        writeWeight(value.toDouble())
                    }
                    34 -> {
                        writeWheelChairPushes(value.toLong())
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
                    "Cervical Mucus" -> {
                        binding.edtHD.hint = "Appearance & Sensation"
                        readCervicalMucus()
                    }
                    "Cycling Pedaling" -> {
                        binding.edtHD.hint = "RPM"
                        readCyclingPedaling()
                    }
                    "Distance Record" -> {
                        binding.edtHD.hint = "Distance (meter)"
                        readDistance()
                    }
                    "Elevation Gained" -> {
                        binding.edtHD.hint = "Elevation Length (m)"
                        readElevationGained()
                    }
                    "Exercise Record" -> {
                        binding.edtHD.hint = "Title"
                        readExerciseSession()
                    }
                    "Floor Climbed" -> {
                        binding.edtHD.hint = "Floors"
                        readFloorsClimbed()
                    }

                    "Heart Rate" -> {
                        binding.edtHD.hint = "Heart Rate"
                        readHeartRate()
                    }
                    "Height" -> {
                        binding.edtHD.hint = "Height (m)"
                        readHeight()
                    }
                    "Hydration" -> {
                        binding.edtHD.hint = "Add Water (liters)"
                        readHydration()
                    }
                    "Lean Body Mass" -> {
                        binding.edtHD.hint = "Lean Body Mass (KG)"
                        readLeanBodyMass()
                    }
                    "Menstruation Flow" -> {
                        binding.edtHD.hint = "Menstruation Flow"
                        readMenstruationFlow()
                    }
                    "Nutrition" -> {
                        binding.edtHD.hint = "Meal Name (sample data)"
                        readNutrition()
                    }
                    "Ovulation Test" -> {
                        binding.edtHD.hint = "Ovulation Test"
                        readOvulation()
                    }
                    "Oxygen Saturation" -> {
                        binding.edtHD.hint = "Percentage %"
                        readOxygenSaturation()
                    }
                    "Power Record" -> {
                        binding.edtHD.hint = "Power in Kcal/Day"
                        readPower()
                    }
                    "Respiratory Rate" -> {
                        binding.edtHD.hint = "Respiration Rate"
                        readRespiration()
                    }
                    "Resting Heart Rate" -> {
                        binding.edtHD.hint = "BPM"
                        readRestingHeartRate()
                    }
                    "Sexual Activity" -> {
                        binding.edtHD.hint = "Sexual Activity"
                        readSexualActivity()
                    }
                    "Sleep Session" -> {
                        binding.edtHD.hint = "Title"
                        readSleepSession()
                    }
                    "Sleep Stage" -> {
                        binding.edtHD.hint = "Sleep Stage"
                        readSleepStage()
                    }
                    "Speed Record" -> {
                        binding.edtHD.hint = "Speed (kmh)"
                        readSpeed()
                    }

                    "Steps Cadence" -> {
                        binding.edtHD.hint = "Rate Step"
                        readStepsCadence()
                    }
                    "Steps Record" -> {
                        binding.edtHD.hint = "Total Count"
                        readSteps()
                    }
                    "Total Calories Burned" -> {
                        binding.edtHD.hint = "Total Kcal Burned"
                        readTotalCaloriesBurned()
                    }
                    "Vo2Max" -> {
                        binding.edtHD.hint = "Vo2Max"
                        readVo2Max()
                    }
                    "Weight" -> {
                        binding.edtHD.hint = "Weight"
                        readWeight()
                    }
                    "Wheelchair Pushes" -> {
                        binding.edtHD.hint = "WheelChair Pushes"
                        readWheelChairPushes()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun writeWheelChairPushes(value: Long) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeWheelChairPushes(
                listOf(
                    WheelchairPushesRecord(
                        count = value,
                        startTime = time.toInstant(),
                        endTime = time.plusMinutes(15).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusMinutes(15).offset
                    )
                )
            )
            readWheelChairPushes()
        }
    }

    private fun writeWeight(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeWeight(
                listOf(
                    WeightRecord(
                        weight = Mass.kilograms(value),
                        time.toInstant(),
                        zoneOffset = time.offset,
                    )
                )
            )
            readWeight()
        }
    }

    private fun readWheelChairPushes() {
        lifecycleScope.launch {
            homeViewModel.readWheelChairPushes(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.wheelchairPushes.observe(this@ReadWriteData) {
            binding.rvData.adapter = WheelChairPushes(it)
        }
    }

    private fun readWeight() {
        lifecycleScope.launch {
            homeViewModel.readWeight(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.weight.observe(this@ReadWriteData) {
            binding.rvData.adapter = WeightAdapter(it)
        }
    }

    private fun writeVo2Max(vo2Max: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeVo2Max(
                listOf(
                    Vo2MaxRecord(
                        vo2MillilitersPerMinuteKilogram = vo2Max,
                        measurementMethod = Vo2MaxRecord.MeasurementMethod.HEART_RATE_RATIO,
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readVo2Max()
        }
    }

    private fun readVo2Max() {
        lifecycleScope.launch {
            homeViewModel.readVo2Max(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.vo2Max.observe(this@ReadWriteData) {
            binding.rvData.adapter = Vo2MaxAdapter(it)
        }
    }

    private fun writeTotalCaloriesBurned(kcal: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeTotalCaloriesBurned(
                listOf(
                    TotalCaloriesBurnedRecord(
                        Energy.kilocalories(kcal),
                        startTime = time.toInstant(),
                        endTime = time.plusHours(7).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(7).offset,
                    )
                )
            )
            readTotalCaloriesBurned()
        }
    }

    private fun readTotalCaloriesBurned() {
        lifecycleScope.launch {
            homeViewModel.readTotalCaloriesBurned(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.totalCaloriesBurnedRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = TotalCaloriesBurnedAdapter(it)
        }
    }

    private fun writeSteps(value: Long) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeSteps(
                listOf(
                    StepsRecord(
                        value,
                        startTime = time.toInstant(),
                        endTime = time.plusHours(7).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(7).offset,
                    )
                )
            )
            readSteps()
        }
    }

    private fun readSteps() {
        lifecycleScope.launch {
            homeViewModel.readStep(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.step.observe(this@ReadWriteData) {
            binding.rvData.adapter = StepAdapter(it)
        }
    }

    private fun writeStepsCadence(rate: Double) {
        val time = ZonedDateTime.now()
        val sample = listOf(
            StepsCadenceRecord.Sample(
                rate = rate,
                time = time.toInstant()
            )
        )
        lifecycleScope.launch {
            homeViewModel.writeStepsCadence(
                listOf(
                    StepsCadenceRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusMinutes(30).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusMinutes(30).offset,
                        samples = sample
                    )
                )
            )
            readStepsCadence()
        }
    }

    private fun readStepsCadence() {
        lifecycleScope.launch {
            homeViewModel.readStepsCadence(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.stepCadence.observe(this@ReadWriteData) {
            binding.rvData.adapter = StepsCadenceAdapter(it)
        }
    }

    private fun writeSpeed(speed: Double) {
        val time = ZonedDateTime.now()
        val sample = listOf(
            SpeedRecord.Sample(
                speed = Velocity.kilometersPerHour(speed),
                time = time.toInstant()
            )
        )
        lifecycleScope.launch {
            homeViewModel.writeSpeed(
                listOf(
                    SpeedRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusMinutes(30).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusMinutes(30).offset,
                        samples = sample
                    )
                )
            )
            readSpeed()
        }
    }

    private fun readSpeed() {
        lifecycleScope.launch {
            homeViewModel.readSpeed(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.speed.observe(this@ReadWriteData) {
            binding.rvData.adapter = SpeedAdapter(it)
        }
    }

    private fun writeSleepStage(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeSleepStage(
                listOf(
                    SleepStageRecord(
                        SleepStageRecord.StageType.DEEP,
                        startTime = time.toInstant(),
                        endTime = time.plusHours(7).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(7).offset,
                    )
                )
            )
            readSleepStage()
        }
    }

    private fun readSleepStage() {
        lifecycleScope.launch {
            homeViewModel.readSleepStage(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.sleepStage.observe(this@ReadWriteData) {
            binding.rvData.adapter = SleepStageAdapter(it)
        }
    }

    private fun writeSleepSession(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeSleepSession(
                listOf(
                    SleepSessionRecord(
                        value,
                        "Default Notes",
                        startTime = time.toInstant(),
                        endTime = time.plusHours(7).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(7).offset,
                    )
                )
            )
            readSleepSession()
        }
    }

    private fun readSleepSession() {
        lifecycleScope.launch {
            homeViewModel.readSleepSession(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.sleepSession.observe(this@ReadWriteData) {
            binding.rvData.adapter = SleepSessionAdapter(it)
        }
    }

    private fun writeSexualActivity(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeSexualActivity(
                listOf(
                    SexualActivityRecord(
                        SexualActivityRecord.Protection.PROTECTED,
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readSexualActivity()
        }
    }

    private fun readSexualActivity() {
        lifecycleScope.launch {
            homeViewModel.readSexualActivity(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.sexualActivityRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = SexualActivityAdapter(it)
        }
    }

    private fun writeRestingHeartRate(value: Long) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeRestingHeartRate(
                listOf(
                    RestingHeartRateRecord(
                        value,
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readRestingHeartRate()
        }
    }

    private fun readRestingHeartRate() {
        lifecycleScope.launch {
            homeViewModel.readRestingHeartRate(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.restingHeartRate.observe(this@ReadWriteData) {
            binding.rvData.adapter = RestingHeartRateAdapter(it)
        }
    }

    private fun writeRespiration(resp: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeRespirationRate(
                listOf(
                    RespiratoryRateRecord(
                        resp,
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readRespiration()
        }
    }

    private fun readRespiration() {
        lifecycleScope.launch {
            homeViewModel.readRespirationRate(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.respirationRate.observe(this@ReadWriteData) {
            binding.rvData.adapter = RespirationAdapter(it)
        }
    }

    private fun writePower(value: Double) {
        val time = ZonedDateTime.now()
        val sample = listOf(
            PowerRecord.Sample(
                time.toInstant(),
                Power.kilocaloriesPerDay(value)
            )
        )
        lifecycleScope.launch {
            homeViewModel.writePower(
                listOf(
                    PowerRecord(

                        startTime = time.toInstant(),
                        endTime = time.plusHours(2).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.offset,
                        samples = sample
                    )
                )
            )
            readPower()
        }
    }

    private fun readPower() {
        lifecycleScope.launch {
            homeViewModel.readPower(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.power.observe(this@ReadWriteData) {
            binding.rvData.adapter = PowerAdapter(it)
        }
    }

    private fun writeOxygenSaturation(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeOxygenSaturation(
                listOf(
                    OxygenSaturationRecord(
                        Percentage(value),
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readOxygenSaturation()
        }
    }

    private fun readOxygenSaturation() {
        lifecycleScope.launch {
            homeViewModel.readOxygenSaturation(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.oxygenSaturationRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = OxygenSaturationAdapter(it)
        }
    }

    private fun writeOvulation(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeOvulationTest(
                listOf(
                    OvulationTestRecord(
                        OvulationTestRecord.Result.HIGH,
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readOvulation()
        }
    }

    private fun readOvulation() {
        lifecycleScope.launch {
            homeViewModel.readOvulationTest(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.ovulationTest.observe(this@ReadWriteData) {
            binding.rvData.adapter = OvulationAdapter(it)
        }
    }

    private fun writeNutrition(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeNutrition(
                listOf(
                    NutritionRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusHours(6).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(6).offset,
                        totalCarbohydrate = Mass.grams(1200.0),
                        name = value,
                        biotin = Mass.grams(19.0),
                        caffeine = Mass.grams(11.0)
                    )
                )
            )
            readNutrition()
        }
    }

    private fun readNutrition() {
        lifecycleScope.launch {
            homeViewModel.readNutrition(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.nutrition.observe(this@ReadWriteData) {
            binding.rvData.adapter = NutritionAdapter(it)
        }
    }

    private fun writeMenstruationFlow(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeMenstruationFlow(
                listOf(
                    MenstruationFlowRecord(
                        MenstruationFlowRecord.Flow.LIGHT,
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readMenstruationFlow()
        }
    }

    private fun readMenstruationFlow() {
        lifecycleScope.launch {
            homeViewModel.readMenstruationFlow(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.menstruationFlow.observe(this@ReadWriteData) {
            binding.rvData.adapter = MenstruationFlowAdapter(it)
        }
    }

    private fun writeLeanBodyMass(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeLeanBodyMass(
                listOf(
                    LeanBodyMassRecord(
                        Mass.kilograms(value),
                        time.toInstant(),
                        time.offset
                    )
                )
            )
            readLeanBodyMass()
        }
    }

    private fun readLeanBodyMass() {
        lifecycleScope.launch {
            homeViewModel.readLeanBodyMass(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.leanBodyMass.observe(this@ReadWriteData) {
            binding.rvData.adapter = LeanBodyMassAdapter(it)
        }
    }

    private fun writeHydration(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeHydration(
                listOf(
                    HydrationRecord(
                        volume = Volume.liters(value),
                        startTime = time.toInstant(),
                        endTime = time.plusHours(4).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(4).offset
                    )
                )
            )
            readHydration()
        }
    }

    private fun readHydration() {
        lifecycleScope.launch {
            homeViewModel.readHydration(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.hydration.observe(this@ReadWriteData) {
            binding.rvData.adapter = HydrationAdapter(it)
        }
    }

    private fun readHeight() {
        lifecycleScope.launch {
            homeViewModel.readHeight(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.height.observe(this@ReadWriteData) {
            binding.rvData.adapter = HeightAdapter(it)
        }
    }

    private fun writeHeight(height: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeHeight(
                listOf(
                    HeightRecord(
                        height = Length.meters(height),
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readHeight()
        }
    }

    private fun writeFloorsClimbed(value: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeFloorsClimbed(
                listOf(
                    FloorsClimbedRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusHours(2).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(2).offset,
                        floors = value
                    )
                )
            )
            readFloorsClimbed()
        }
    }

    private fun readFloorsClimbed() {
        lifecycleScope.launch {
            homeViewModel.readFloorsClimbed(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.floorsClimbedRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = FloorsClimbedAdapter(it)
        }
    }

    private fun readExerciseSession() {
        lifecycleScope.launch {
            homeViewModel.readExerciseSession(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.exerciseSession.observe(this@ReadWriteData) {
            binding.rvData.adapter = ExerciseSessionAdapter(it)
        }
    }

    private fun writeExerciseSession(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeExerciseSession(
                listOf(
                    ExerciseSessionRecord(
                        startTime = time.toInstant(),
                        endTime = time.plusHours(2).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(2).offset,
                        title = value,
                        exerciseType = ExerciseSessionRecord.ExerciseType.RUNNING,
                        notes = "No Notes"
                    )
                )
            )
            readExerciseSession()
        }
    }

    private fun writeElevationGained(elevation: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeElevationGained(
                listOf(
                    ElevationGainedRecord(
                        Length.meters(elevation),
                        startTime = time.toInstant(),
                        endTime = time.plusHours(2).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(2).offset
                    )
                )
            )
            readElevationGained()
        }
    }

    private fun readElevationGained() {
        lifecycleScope.launch {
            homeViewModel.readElevationGained(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.elevationGainedRecord.observe(this@ReadWriteData) {
            binding.rvData.adapter = ElevationGainedAdapter(it)
        }
    }

    private fun writeDistance(distance: Double) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeDistance(
                listOf(
                    DistanceRecord(
                        distance = Length.meters(distance),
                        startTime = time.toInstant(),
                        endTime = time.plusHours(2).toInstant(),
                        startZoneOffset = time.offset,
                        endZoneOffset = time.plusHours(2).offset
                    )
                )
            )
            readDistance()
        }
    }

    private fun readDistance() {
        lifecycleScope.launch {
            homeViewModel.readDistance(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.distance.observe(this@ReadWriteData) {
            binding.rvData.adapter = DistanceAdapter(it)
        }
    }

    private fun readCyclingPedaling() {
        lifecycleScope.launch {
            homeViewModel.readCyclingPedaling(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.cyclingPedaling.observe(this@ReadWriteData) {
            binding.rvData.adapter = CyclingPedalingAdapter(it)
        }
    }

    private fun writeCyclingPedaling(x: Double) {
        val time = ZonedDateTime.now()
        val sample = listOf(
            CyclingPedalingCadenceRecord.Sample(
                time.toInstant(),
                x
            )
        )
        lifecycleScope.launch {
            homeViewModel.writeCyclingPedaling(
                listOf(
                    CyclingPedalingCadenceRecord(
                        samples = sample,
                        startTime = time.toInstant(),
                        endZoneOffset = time.plusHours(1).offset,
                        startZoneOffset = time.offset,
                        endTime = time.plusHours(2).toInstant()
                    )
                )
            )
            readCyclingPedaling()
        }
    }

    private fun readCervicalMucus() {
        lifecycleScope.launch {
            homeViewModel.readCervicalMucus(
                ZonedDateTime.now().minusDays(7),
                ZonedDateTime.now()
            )
        }
        binding.rvData.layoutManager = LinearLayoutManager(this)
        homeViewModel.cervicalMucus.observe(this@ReadWriteData) {
            binding.rvData.adapter = CervicalMucusAdapter(it)
        }
    }

    private fun writeCervicalMucus(value: String) {
        val time = ZonedDateTime.now()
        lifecycleScope.launch {
            homeViewModel.writeCervicalMucus(
                listOf(
                    CervicalMucusRecord(
                        CervicalMucusRecord.Appearance.CREAMY,
                        CervicalMucusRecord.Sensation.HEAVY,
                        time = time.toInstant(),
                        zoneOffset = time.offset
                    )
                )
            )
            readCervicalMucus()
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


