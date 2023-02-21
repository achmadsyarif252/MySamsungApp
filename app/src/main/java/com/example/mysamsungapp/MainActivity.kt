package com.example.mysamsungapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.*
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.health.connect.client.units.Energy
import androidx.health.connect.client.units.Mass
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysamsungapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.time.Instant
import java.time.ZonedDateTime
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.data.layoutManager = LinearLayoutManager(this)
//        updateData()
//        updateDataHR()
//        updateStepData()

        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
//                writeHeartRate(
//                    ZonedDateTime.now(),
//                    ZonedDateTime.now().plusSeconds(10),
//                    binding.weightValue.text.toString().toLong()
//                )
                writeStep(ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),binding.weightValue.text.toString().toLong())
                withContext(Dispatchers.Main) {
//                    updateData()
//                    updateDataHR()
//                    updateStepData()
                }
            }
        }
    }

//    fun updateData() {
//        lifecycleScope.launch(Dispatchers.Default) {
//            val listWeight = readWeightInput(
//                ZonedDateTime.now().minusDays(7).toInstant(),
//                ZonedDateTime.now().toInstant()
//            )
//            withContext(Dispatchers.Main) {
//                binding.data.adapter = ListWeightAdapter(listWeight)
//            }
//        }
//    }

//    fun updateDataHR() {
//        lifecycleScope.launch(Dispatchers.Default) {
//            val listHR = readHeartRate(
//                ZonedDateTime.now().minusDays(7).toInstant(),
//                ZonedDateTime.now().toInstant()
//            )
//            withContext(Dispatchers.Main) {
//                binding.data.adapter = ListWeightAdapter(listHR)
//            }
//        }
//    }
//
//    fun updateStepData() {
//        lifecycleScope.launch(Dispatchers.Default) {
//            val listStep = readStepsByTimeRange(
//                healthConnectClient,
//                ZonedDateTime.now().minusDays(7).toInstant(),
//                ZonedDateTime.now().toInstant()
//            )
//            withContext(Dispatchers.Main) {
//                binding.data.adapter = HeartRateAdapter(listStep)
//            }
//        }
//    }

    suspend fun readWeightInput(start: Instant, end: Instant): List<WeightRecord> {
        val request = ReadRecordsRequest(
            recordType = WeightRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        return response.records
    }

    suspend fun readHeartRate(start: Instant, end: Instant): List<HeartRateRecord> {
        val request = ReadRecordsRequest(
            recordType = HeartRateRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        return response.records
    }

    suspend fun writeWeightInput(weightInput: Double) {
        val time = ZonedDateTime.now().withNano(0)
        val weightRecord = WeightRecord(
            weight = Mass.kilograms(weightInput),
            time = time.toInstant(),
            zoneOffset = time.offset
        )
        val weightRecord2 = WeightRecord(
            weight = Mass.kilograms(weightInput + 1),
            time = time.minusDays(3).toInstant(),
            zoneOffset = time.offset
        )
        val records = listOf(weightRecord, weightRecord2)
        try {
            healthConnectClient.insertRecords(records)
        } catch (e: Exception) {
            print("Error")
        }
    }

    suspend fun writeHeartRate(start: ZonedDateTime, end: ZonedDateTime, bpm: Long) {
        healthConnectClient.insertRecords(
            listOf(
                HeartRateRecord(
                    startTime = start.toInstant(),
                    endTime = end.toInstant(),
                    samples = listOf(
                        HeartRateRecord.Sample(
                            time = start.toInstant(),
                            beatsPerMinute = bpm
                        )
                    ),
                    startZoneOffset = start.offset,
                    endZoneOffset = end.offset,
                )
            )
        )
    }

    suspend fun writeStep(start: ZonedDateTime, end: ZonedDateTime,count:Long) {
        healthConnectClient.insertRecords(
            listOf(
                StepsRecord(
                    count = count,
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endZoneOffset = end.offset,
                    endTime = end.toInstant()
                )
            )
        )
    }

    suspend fun readStepsByTimeRange(
        healthConnectClient: HealthConnectClient,
        startTime: Instant,
        endTime: Instant
    ): List<StepsRecord> {
        val response =
            healthConnectClient.readRecords(
                ReadRecordsRequest(
                    StepsRecord::class,
                    timeRangeFilter = TimeRangeFilter.between(startTime, endTime)
                )
            )
        return response.records
    }


    suspend fun writeExerciseSession(start: ZonedDateTime, end: ZonedDateTime) {
        healthConnectClient.insertRecords(
            listOf(
                ExerciseSessionRecord(
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    exerciseType = ExerciseSessionRecord.ExerciseType.RUNNING,
                    title = "My Run #${Random.nextInt(0, 60)}"
                ),
                StepsRecord(
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    count = (1000 + 1000 * Random.nextInt(3)).toLong()
                ),
                TotalCaloriesBurnedRecord(
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    energy = Energy.calories((140 + Random.nextInt(20)) * 0.01)
                )
            ) + buildHeartRateSeries(start, end)
        )
    }

    /**
     * TODO: Build [HeartRateRecord].
     */
    private fun buildHeartRateSeries(
        sessionStartTime: ZonedDateTime,
        sessionEndTime: ZonedDateTime,
    ): HeartRateRecord {
        val samples = mutableListOf<HeartRateRecord.Sample>()
        var time = sessionStartTime
        while (time.isBefore(sessionEndTime)) {
            samples.add(
                HeartRateRecord.Sample(
                    time = time.toInstant(),
                    beatsPerMinute = (80 + Random.nextInt(80)).toLong()
                )
            )
            time = time.plusSeconds(30)
        }
        return HeartRateRecord(
            startTime = sessionStartTime.toInstant(),
            startZoneOffset = sessionStartTime.offset,
            endTime = sessionEndTime.toInstant(),
            endZoneOffset = sessionEndTime.offset,
            samples = samples
        )
    }
}